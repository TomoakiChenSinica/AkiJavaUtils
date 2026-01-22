# NamingStrategy 設計議題討論紀錄

> 討論日期：2026-01-22

## 背景

在整理 AkiDataFileSystem 的 Javadoc 時，發現 `DataFileNamingStrategy` 的設計定位有些矛盾。

## 目前設計

- `DataFileNamingStrategy<T>` - 根據 data 產生檔名的策略 interface
- `UUIDNamingStrategy` - 實作，用 UUID 產生檔名
- `NewDataFilePathProvider.obtainNewFilePath(data)` - 內部透過 NamingStrategy 產生檔名

## 發現的問題

1. **檔名寫回的問題**：NamingStrategy 產生檔名後，沒有介面可以把檔名寫回 `DataFileRelation`
2. **DataFileRelation 的定位**：如果檔名由 NamingStrategy 決定，那 `DataFileRelation.getFileRealName()` 的值從哪來？
3. **彈性問題**：使用者自己設定 `fileRealName` 可能更有彈性，不一定需要 NamingStrategy

## 討論的選項

### 選項 A：保留 NamingStrategy 作為核心
- Lib 負責產生檔名
- 需要新增 setter 介面把檔名寫回 dataEntity

### 選項 B：移除 NamingStrategy（降級為 Utility）✓ 傾向此方案
- 使用者自行設定 `fileRealName`
- Lib 只從 `getFileRealName()` 讀取
- `UUIDNamingStrategy` 保留為 utility class，供需要的人使用

### 選項 C：共存
- 先檢查 `dataEntity.getFileRealName()` 有無值
- 有 → 使用者已設定，直接用
- 無 → 用 NamingStrategy 產生

## 檔名欄位的資料流向問題

另一個相關問題：`fileRealName` 欄位的值是怎麼來的？

| 方式 | 流程 | 風險 |
|------|------|------|
| 先存檔 → 再記錄 | 產生檔名 → 存檔案 → 成功後寫 DB | DB 失敗會有孤兒檔案 |
| 先記錄 → 再存檔 | 產生檔名 → 寫 DB → 存檔案 | 存檔失敗會有假記錄 |

**結論**：實務上「先存檔 → 再記錄」較常見。但 Lib 不需要管這個順序，讓使用者決定。

## 暫定結論

1. **NamingStrategy 降級為 Utility**（遵循 YAGNI 原則）
2. **Lib 職責單純化**：
   - 輸入：dataEntity（已有 fileRealName）+ InputStream
   - 輸出：存檔到對應路徑
3. **使用者責任**：自行決定何時設定 fileRealName、何時存 DB

## 待決定事項

- [ ] 確認是否完全移除 NamingStrategy 作為核心依賴
- [ ] 決定 UUIDNamingStrategy 的最終定位（保留為 utility 或完全移除）
- [ ] 調整相關 interface 的設計（如 NewDataFilePathProvider）

## 相關檔案

- `tw.dev.tomoaki.datafilesystem.core.DataFileNamingStrategy`
- `tw.dev.tomoaki.datafilesystem.core.naming.UUIDNamingStrategy`
- `tw.dev.tomoaki.datafilesystem.core.NewDataFilePathProvider`
- `tw.dev.tomoaki.datafilesystem.core.entity.DataFileRelation`
