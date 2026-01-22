# AkiDataFileSystem

> 數據驅動的文件系統管理框架

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-1.8+-orange.svg)](https://www.oracle.com/java/)
[![Version](https://img.shields.io/badge/Version-0.5.0--SNAPSHOT-green.svg)](https://github.com)

## 專案簡介

AkiDataFileSystem 是一個高度抽象的文件系統管理框架，用於建立資料對象與實際文件之間的關聯關係。該框架提供了完整的文件生命周期管理、多類型文件支持、安全路徑控制以及可自訂的檔名生成策略，適用於需要複雜文件管理需求的企業級應用。

## 核心功能

- **數據與文件關聯管理**：透過 `DataFileRelation<DATA>` 建立主資料（DATA）與檔案資料（DATA_FILE）的一對多關係
- **文件生命周期管理**：統一路徑管理、文件保存與刪除
- **可自訂檔名策略**：透過 `DataFileNamingStrategy` 介面自訂檔名生成邏輯，支援副檔名處理
- **多類型文件支持**：透過 `DataRelatedTypedFilePathProvider` 實現不同類型文件的分類存儲
- **安全性與權限檢查**：防止路徑遍歷攻擊、讀寫權限判斷、非法路徑訪問保護
- **遠程文件操作**：支持與遠程文件系統的交互（複製、刪除等）
- **高度可擴展**：基於接口和抽象類設計，易於擴展和定制

## 應用場景

- **內容管理系統（CMS）**：管理文章的附件、封面圖片等關聯文件
- **文檔管理系統**：處理用戶上傳的各類文檔，支持版本控制
- **多媒體應用**：圖片、視頻、音頻等不同類型文件的分類管理
- **數據備份與歸檔**：本地-遠程文件同步、文件備份與恢復

## 架構設計

### 分層架構

```
┌─────────────────────────────────────────┐
│          應用層 (Application)            │
├─────────────────────────────────────────┤
│      捆綁層 (Bundle) - 具體實現          │
│  • DataRelatedFileManager              │
│  • DataRelatedFilePathProvider         │
│  • DataRelatedTypedFilePathProvider    │
├─────────────────────────────────────────┤
│        核心層 (Core) - 接口定義          │
│  • DataFileManager                     │
│  • DataFileNamingStrategy              │
│  • RecentDataFilePathProvider          │
│  • NewDataFilePathProvider             │
├─────────────────────────────────────────┤
│      命名策略 (Naming) - 檔名生成        │
│  • UUIDNamingStrategy                  │
├─────────────────────────────────────────┤
│      輔助層 (Helper) - 工具類            │
│  • DataFileRelationHelper              │
└─────────────────────────────────────────┘
```

### 核心組件

#### 1. 核心接口

- **`DataFile`**：最底層的文件接口，定義文件顯示名稱和實際名稱
- **`DataFileRelation<DATA>`**：檔案資料接口，`<DATA>` 表示此檔案「屬於」的主資料類型，用於建立 DATA 與檔案的一對多關係
- **`DataFileManager<T>`**：文件管理接口，提供保存、刪除等操作
- **`DataFileNamingStrategy<T>`**：檔名生成策略接口，負責根據資料生成檔名
- **`RecentDataFilePathProvider<T>`**：獲取已存在文件的路徑
- **`NewDataFilePathProvider<T>`**：生成新文件的路徑

#### 2. 檔名策略 (Naming Strategy)

- **`UUIDNamingStrategy`**：預設策略，使用 UUID 作為檔名
- 可自訂實作 `DataFileNamingStrategy` 來定義命名邏輯

#### 3. 具體實現（Bundle 層）

- **`DataRelatedFilePathProvider`**：基礎路徑提供者，適用於 DATA 與 DATA_FILE 一對多關係
- **`DataRelatedTypedFilePathProvider`**：支持文件類型的高級路徑提供者
- **`DataRelatedFileManager`**：文件管理器的具體實現

#### 4. 遠程操作

- **`DataRemoteFileManager<T>`**：遠程文件管理接口
- **`DataRemoteFile`**：遠程文件實體

## 快速開始

### Maven 依賴

```xml
<dependency>
    <groupId>tw.dev.tomoaki</groupId>
    <artifactId>AkiDataFileSystem</artifactId>
    <version>0.5.0-SNAPSHOT</version>
</dependency>
```

### 基本使用範例

#### 1. 定義資料實體

```java
// 主資料實體
public class Article {
    private Long id;
    private String title;
    private ArticleFile coverImage;

    // getters and setters...
}

// 檔案資料實體（屬於 Article 的檔案）
public class ArticleFile implements DataFileRelation<Article> {
    private String fileName;
    private String displayName;

    @Override
    public String getFileDisplayName() {
        return displayName;
    }

    @Override
    public String getFileRealName() {
        return fileName;
    }

    // getters and setters...
}
```

#### 2. 實現路徑提供者

```java
public class ArticleFilePathProvider
    extends DataRelatedFilePathProvider<Article, ArticleFile> {

    @Override
    protected String getFileRoot() {
        return "/var/data/articles";
    }

    @Override
    protected ArticleFile obtainDataFile(Article article) {
        return article.getCoverImage();
    }
}
```

#### 3. 使用文件管理器

```java
// 初始化
ArticleFilePathProvider pathProvider = new ArticleFilePathProvider();
DataRelatedFileManager<Article, ArticleFile> fileManager =
    new DataRelatedFileManager<>(pathProvider);

// 保存文件
Article article = new Article();
article.setTitle("我的文章");

try (InputStream inputStream = new FileInputStream("cover.jpg")) {
    File savedFile = fileManager.save(article, inputStream, false);
    System.out.println("文件已保存至: " + savedFile.getAbsolutePath());
}

// 刪除文件
File deletedFile = fileManager.delete(article);
```

#### 4. 自訂檔名策略

```java
// 實作自訂的檔名策略
public class TimestampNamingStrategy<T> implements DataFileNamingStrategy<T> {

    @Override
    public String createFileName(T data) {
        return "file_" + System.currentTimeMillis();
    }
}

// 使用自訂策略
ArticleFilePathProvider pathProvider = new ArticleFilePathProvider(
    new TimestampNamingStrategy<>()
);

// 產生帶副檔名的檔名
String fileName = pathProvider.createFileName(article, "jpg");
// 結果: file_1705123456789.jpg
```

#### 5. 多類型文件支持

```java
public class MediaFilePathProvider
    extends DataRelatedTypedFilePathProvider<Media, MediaFile, MediaType> {

    @Override
    protected String getFileRoot(MediaType type) {
        switch (type) {
            case IMAGE:
                return "/var/data/images";
            case VIDEO:
                return "/var/data/videos";
            case AUDIO:
                return "/var/data/audio";
            default:
                return "/var/data/others";
        }
    }

    @Override
    protected MediaType obtainFileType(MediaFile mediaFile) {
        return mediaFile.getType();
    }

    @Override
    protected MediaFile obtainDataFile(Media media, MediaType fileType) {
        return media.getFileByType(fileType);
    }
}
```

## 安全特性

### 路徑遍歷攻擊防護

框架會自動檢查生成的文件路徑是否在允許的根目錄內，防止惡意路徑訪問：

```java
// 自動檢查，如果路徑超出根目錄會拋出 FileAccessDeninedException
Path newPath = pathProvider.obtainNewFilePath(data);
```

### 權限檢查

在保存文件前會檢查目錄的讀寫權限：

```java
if (!Files.isWritable(parentDir)) {
    throw new IOException("無寫入權限: " + parentDir);
}
```

## 設計模式

- **策略模式（Strategy Pattern）**：`DataFileNamingStrategy` 允許不同的檔名生成策略互換
- **工廠模式（Factory Pattern）**：`DataRemoteFile.Factory` 創建遠程文件對象
- **模板方法模式（Template Method Pattern）**：抽象類定義框架，子類實現具體邏輯
- **泛型編程**：類型安全的代碼重用

## 專案依賴

| 依賴 | 版本 | 用途 |
|------|------|------|
| `AkiFileIOUtils` | [1.2.1-SNAPSHOT,) | 提供底層文件讀寫工具 |
| `AkiCommonDataValidator` | 1.0-SNAPSHOT | 提供數據驗證功能 |

## 版本歷史

### 0.5.0-SNAPSHOT (當前版本)
- 重構檔名生成機制
  - `DataFileCreator` 改名為 `DataFileNamingStrategy`
  - `NewDataFilePathProvider` 不再繼承 `DataFileNamingStrategy`，改為組合模式
  - 新增 `core.naming` package 放置命名策略實作
  - 新增 `UUIDNamingStrategy` 作為預設策略
- 支援帶副檔名的檔名生成：`createFileName(data, extension)`
- 改善路徑安全檢查的日誌輸出

### 0.4.1
- 修正 bug、補充註解

### 0.4.0
- 擴充 `filesystem.bundle` 底下的 `XxxxPathProvider` 功能

### 0.3.1
- 加入讀取/寫入路徑的權限判斷
- 完善 `AkiSimpleFileManager` 功能

## 開發與構建

### 環境要求

- Java 1.8 或更高版本
- Maven 3.x

### 編譯專案

```bash
mvn clean install
```

### 運行測試

```bash
mvn test
```

## 發佈倉庫

本專案發佈至 CCS Git Repository：
```
https://ccs-git.iis.sinica.edu.tw
```

## 授權許可

本專案採用 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) 授權。

```
Copyright 2025 Tomoaki Chen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## 貢獻指南

歡迎提交 Issue 或 Pull Request 來改進本專案。

## 聯絡方式

- **Group ID**: tw.dev.tomoaki
- **Repository**: CCS Git Repository (IIS, Sinica)

---

**注意**：本專案為 SNAPSHOT 版本，API 可能會有變動，建議在生產環境使用穩定版本。
