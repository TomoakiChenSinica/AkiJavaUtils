JSON Schema Lib

### 起源

* 有一些地方用 JSON 存(主要是想方便拓展欄位)，希望有個東西類似來定義這個 JSON 的內容(有哪一些欄位、用途描述等等)

* JSON Schema 當時創建(?)好像本來就是類似用途

* 可以確定的是，他可以拿來當成驗證 JSON **資料**正不正確的**規格**。

### 目標

* 利用 JSON Schema 產生「(網頁)填寫頁面」，描述哪一些欄位、欄位描述。

* 利用 JSON Schema 檢查資料。

* 利用 JSON Entity 存此資料，並轉成 JSON String 存進 DB。

### 筆記

* JSON Schema 和 JSON Entity 的關係
  
  * 官方 GitHub 的例子似乎是從 JSON Entity $\to$ JSON Schema ?
  
  * 但我比較想讓 JSON Schema 是進一步描寫 JSON Entity 的欄位意義的規格檔。






















