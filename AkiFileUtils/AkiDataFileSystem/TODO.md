# TODO List

AkiDataFileSystem 專案待辦事項與改進計劃

---

## 🔴 高優先級（影響穩定性和安全性）

### Bug 修復
- [ ] **修復 delete() 方法缺少返回值檢查**
  - 位置：`DataRelatedFileManager.delete()` (line 70)
  - 問題：`file.delete()` 沒有檢查返回值
  - 建議：檢查返回值並在失敗時拋出異常
  ```java
  boolean deleted = file.delete();
  if (!deleted) {
      throw new IOException("無法刪除文件: " + file.getAbsolutePath());
  }
  ```

- [ ] **處理 DataFileRelationPathProvider 未完成的實現**
  - 位置：`bundle/DataFileRelationPathProvider.java`
  - 問題：所有方法都拋出 `UnsupportedOperationException`
  - 選項：
    - 實現功能
    - 標註為 `@Deprecated` 並說明原因
    - 改為抽象類別作為框架
    - 直接移除

### 安全性與穩定性
- [ ] **加強 null 檢查和參數驗證**
  - `DataRelatedFileManager.save()` 應驗證 data 和 inputStream 參數
  - `DataRelatedFilePathProvider.obtainDataFile()` 返回值的 null 處理
  - 建議：在所有公開方法入口處添加參數驗證
  ```java
  if (data == null) {
      throw new IllegalArgumentException("data 不能為 null");
  }
  ```

- [ ] **明確 InputStream 的資源管理責任**
  - 在 `DataFileManager.save()` 的 JavaDoc 中說明誰負責關閉 InputStream
  - 或考慮在內部使用 try-with-resources 自動關閉

### 測試
- [ ] **建立測試框架**
  - 創建 `src/test/java` 目錄結構
  - 添加測試依賴（JUnit 5）

- [ ] **核心功能單元測試**
  - [ ] `DataRelatedFilePathProvider` 測試
    - 測試路徑生成邏輯
    - 測試路徑安全驗證（防止路徑遍歷攻擊）
  - [ ] `DataRelatedFileManager` 測試
    - 測試文件保存功能
    - 測試文件刪除功能
    - 測試覆蓋既有文件邏輯
  - [ ] `DataRelatedTypedFilePathProvider` 測試
    - 測試多類型文件路徑生成

- [ ] **異常情況測試**
  - 權限不足時的行為
  - 文件不存在時的行為
  - 磁碟空間不足時的行為
  - 非法路徑的處理

---

## 🟡 中優先級（提升可用性）

### 功能改進
- [ ] **改進檔名生成策略**
  - 問題：目前只支持 UUID，且沒有副檔名
  - 改進方向：
    - [ ] 自動保留/添加副檔名
    - [ ] 支持自定義命名策略（策略模式）
    - [ ] 支持保留原始檔名選項
    - [ ] 支持添加時間戳
  ```java
  // 建議新增
  public interface FileNamingStrategy<T> {
      String generateFileName(T data, String extension);
  }
  ```

- [ ] **更細緻的異常處理**
  - [ ] 定義專用異常類別
    - `FileOperationException` (基礎異常)
    - `FileSaveException`
    - `FileDeleteException`
    - `DuplicateFileException`
    - `InvalidPathException`
  - [ ] 在適當的地方使用更具體的異常

- [ ] **改進 delete() 方法**
  - [ ] 添加「刪除前檢查」選項
  - [ ] 支持刪除後的回調
  - [ ] 考慮添加「軟刪除」選項（移到回收站而非真刪除）

### 文檔完善
- [ ] **補充 JavaDoc**
  - [ ] `DataFile` 接口
  - [ ] `DataFileRelation` 接口
  - [ ] `DataFileManager` 接口所有方法
  - [ ] `DataRelatedFilePathProvider` 抽象類別
  - [ ] `DataRelatedTypedFilePathProvider` 抽象類別
  - [ ] `DataRelatedFileManager` 抽象類別

- [ ] **添加使用範例**
  - [ ] 在 `src/test/java` 中創建 example 包
  - [ ] 提供完整的使用範例程式碼
  - [ ] 涵蓋常見使用場景

- [ ] **補充架構文檔**
  - [ ] 創建 UML 類圖
  - [ ] 說明設計模式的應用
  - [ ] 說明擴展點和最佳實踐

---

## 🟢 低優先級（功能擴展）

### 新功能
- [ ] **文件操作擴展**
  - [ ] 文件重命名 `rename(DATA data, String newName)`
  - [ ] 文件移動 `move(DATA data, Path targetPath)`
  - [ ] 文件複製實現（目前 `DataFileCopier` 只是接口）
  - [ ] 批量操作
    - `batchSave(List<DATA> dataList, ...)`
    - `batchDelete(List<DATA> dataList)`

- [ ] **文件元數據管理**
  - [ ] 獲取文件大小
  - [ ] 獲取文件創建/修改時間
  - [ ] 獲取文件 MIME 類型
  - [ ] 文件校驗和計算（MD5、SHA-256）

- [ ] **文件版本管理**
  - [ ] 保存時自動備份舊版本
  - [ ] 版本歷史查詢
  - [ ] 版本回滾功能

- [ ] **遠程文件功能實現**
  - [ ] 實現 `DataRemoteFileManager` 的具體實現
  - [ ] 支持 FTP/SFTP
  - [ ] 支持 S3/MinIO
  - [ ] 支持 WebDAV

### 性能與並發
- [ ] **並發安全性**
  - [ ] 文件保存時的文件鎖機制
  - [ ] 防止同時寫入同一文件
  - [ ] 考慮添加事務性操作支持

- [ ] **性能優化**
  - [ ] 大文件分片上傳
  - [ ] 異步保存選項
  - [ ] 文件壓縮選項

### 配置靈活性
- [ ] **配置系統**
  - [ ] 支持從配置文件讀取設置
  - [ ] 提供建造者模式（Builder Pattern）
  ```java
  DataRelatedFileManager manager = DataRelatedFileManager.builder()
      .rootPath("/var/data")
      .namingStrategy(NamingStrategy.UUID)
      .overwritePolicy(OverwritePolicy.THROW_EXCEPTION)
      .enableVersionControl(true)
      .build();
  ```

- [ ] **可配置項**
  - [ ] 根目錄路徑
  - [ ] 命名策略
  - [ ] 覆蓋策略（拋異常/自動備份/直接覆蓋）
  - [ ] 文件大小限制
  - [ ] 允許的文件類型白名單/黑名單

---

## 📝 文檔與工具

- [ ] **開發工具**
  - [ ] 添加 `.editorconfig` 統一編碼風格
  - [ ] 添加 Checkstyle 配置
  - [ ] 配置 CI/CD（GitHub Actions / Jenkins）

- [ ] **發佈準備**
  - [ ] 完善 CHANGELOG.md（目前只在 pom.xml 註釋中）
  - [ ] 準備 ROADMAP.md（如果有版本規劃）
  - [ ] 準備 CONTRIBUTING.md（如果開源）

---

## 🐛 已知問題

### 設計問題
- `DataFileRelationPathProvider` 類別未實現任何功能
- 刪除操作不驗證是否成功
- 檔名生成缺少副檔名處理

### 缺失功能
- 缺少單元測試
- 缺少完整的 JavaDoc
- 遠程文件功能僅有接口定義

---

## 💡 未來考慮

### 架構演進
- [ ] 考慮支持插件系統（動態擴展文件存儲後端）
- [ ] 考慮事件系統（文件保存前/後、刪除前/後的事件監聽）
- [ ] 考慮集成流行的存儲框架（Apache Commons VFS）

### 整合
- [ ] Spring Boot Starter
- [ ] Jakarta EE CDI 支持
- [ ] Micronaut 整合

---

## 📌 備註

- 本列表會隨專案發展持續更新
- 優先級可能根據實際需求調整
- 完成項目請勾選 ✓ 並移至 CHANGELOG.md

**最後更新**：2026-01-05
