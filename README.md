# AkiJavaUtils

## 一些建置Web專案常使用的Java Utils / Some Common Utils For Java Web Application

[![](https://jitpack.io/v/TomoakiChenSinica/AkiJavaUtils.svg)](https://jitpack.io/#TomoakiChenSinica/AkiJavaUtils)

### 快速使用方式

* 加入遠端倉庫 / Add Repository

```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

* 使用全部的函示庫 / For All Utils

```xml
<dependency>
    <groupId>com.github.TomoakiChenSinica</groupId>
    <artifactId>AkiJavaUtils</artifactId>
    <version>jitpack-1.1</version>
    <type>pom</type>
</dependency>  
```

```xml
<dependency>
    <groupId>com.github.TomoakiChenSinica.AkiJavaUtils</groupId>
    <artifactId>[Util N]</artifactId>
    <version>jitpack-1.0</version>
</dependency>
```

### 目前共有以下功能

* AkiArticle

* AkiCSSInliner

* AkiCast

* AkiCountryUtils

* AkiDataUtils

* AkiDataValidator
  
  * AkiCommonDataValidator
  
  * AkiIPValidator
  
  * AkiTaiwanDataValidator

* AkiSecurity

* AkiDataValidator

* AkiWeb
  
  * Release Notes
    
    >   1.1版，加上 System Permission Denined
    > 
    >   1.1.1 版，修正改善 System Permission Denined 機制
    > 
    >   1.2 版，再度調整。
    > 
    >   2.0 版，因為直接邏輯順序改變，乾脆跳到這版號
    > 
    >   2.2 版，調整 JavaWebUtils。 Method params 有調整順序
    > 
    >   2.3 版。 JavaWebUtils --> AkiWebUtils
    > 
    >   2.4 版: 改良 UrlProvider
    > 
    >   2.4.1 版: 改 provided
    > 
    >   2.4.2 ~ 2.4.6版: 新增 ProxyRequestHelper
    > 
    >   2.4.7 版: 改良 UrlProvider，提供基礎針對 Proxy 環境下的支援
    > 
    >   2.4.8 版: 改良 UrlAppender，目前的 UrlAppender，無法做到「固定一個URL Header」
    > 
    >   2.5.0 版: 繼續改良UrlProvider，預計可以針對 Proxy 環境下的支援更好 



## 開發流程說明

* 本專案目前單一開發者使用雙帳號進行維護
  
  * [TomoakiChenSinica - tomoaki.sinica@gmail.com](https://github.com/TomoakiChenSinica) 專案擁有者
  
  * [TomoakiChen - archerugly@gmail.com](https://github.com/TomoakiChen) 協作者

* 協作者的變更透過 Pull Request 流程進行，以確保
  
  * 主支為 protected
  
  * 為未來團隊協作做準備(如果可以)