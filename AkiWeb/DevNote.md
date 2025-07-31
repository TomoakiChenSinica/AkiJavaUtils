## 2025-07-31

- #### webservice 包下面的有幾個討論點
  
  - **紀錄者:** tomoaki.sinica@gmail.com
  
  - **描述:** 現在太相依於 `JAX-RS`規範(我們是用 `Jersey` 這個實作)
    
    - 然後有點矛盾或多此一舉的是我們利用 `JAX-RS`，但是**序列化**、**反序列化** (以 JSON 為例)還是透過自己的方案轉。
      
      - 以前就有注意到 (Jersey 的) `readEntity` 理論上可以自己處理要的格式。
      
      - 當然以自己包裝 Client 來講，這部分差異不到太大，頂多 Client 裡面多一行(看寫法)
        
        ```java
        Response response = this.doGet(target, MediaType.APPLICATION_JSON_TYPE); // doGet 是上 WebTarget 做 GET
        ...
        ...
        String result = response.readEntity(String.class);
        ...
        ...
        ARMgmtPaper paper = JsonToJava.getJavaListObject(result , ARMgmtPaper.class); // 變成自己處理，而不是 readEntity 直接轉成 ARMgmtPaper
        ```
    
    - 此規範(及其實作)主要是**Restful** 風格的 WebService ，雖然他 HTTP Client 功能(`WebTarget`)上很好用，但用在比如接 SOAP 似乎會顯得有點不倫不類?


