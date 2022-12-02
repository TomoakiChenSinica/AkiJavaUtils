# 用途
* 當我們要將html資料放到email中，比如gmail會把class attribute給拿掉，導致如果是用class方式設定排版會無法正常顯示。此lib可以協助將class對應的css轉成style attribute 
* 比如原本有一段 html 敘述如下
  ```html
  <style>
  .highlight {
    color: red;
  }

  .bold-text {
    font-size: bold;
  }
  </style>

  <div class="hightlight">
    This is test
  </div>
  <div style="hightlight bold-text">
    This is test
  </div>

  ```

  會轉成

  ```html
  <div style="color:red;">
    This is test
  </div>
  <div class="color:red; font-size:bold;">
    This is test
  </div>

  ```

# 下載方式(maven)

### Jitpack版  [![](https://jitpack.io/v/TomoakiChenSinica/AkiJavaUtils.svg)](https://jitpack.io/#TomoakiChenSinica/AkiJavaUtils)

在pom.xml分別加入以下兩段
```xml
<repositories>
  <repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
  </repository>
</repositories>
```

版本號(version)可見jitpack
```
<dependency>
	<groupId>com.github.TomoakiChenSinica.AkiJavaUtils</groupId>
	<artifactId>AkiCSSInliner</artifactId>
	<version>jitpack-2022.11.23-1</version>
</dependency>
```

