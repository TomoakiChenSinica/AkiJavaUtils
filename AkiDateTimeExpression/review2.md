# AkiDateTimeExpression 重構討論記錄

討論日期：2026-01-12
參與者：開發者 & Claude Code

---

## 一、專案命名討論

### 背景
專案原名為 `AkiDateTimeCloze`，現計劃重新命名以移除學術性術語 "Cloze"。

### 命名候選方案比較

#### 1. DateTimeExpression（開發者提議）
- ✅ 去掉了學術用語 "Cloze"
- ✅ "Expression" 暗示可以進行計算和求值
- ⚠️ 可能讓人聯想到正則表達式（但影響不大）

#### 2. DateTimeTemplate
- ✅ 更準確描述功能：模板填空
- ✅ 業界常見命名慣例
- ✅ 直觀表達「套用參數產生結果」的概念
- ❌ **致命問題**：無法表達「加減運算」能力

#### 3. DateTimePattern
- ✅ 簡潔明瞭
- ⚠️ 可能與日期格式化的 Pattern 混淆

#### 4. DateTimeFormula
- ✅ Formula（公式）明確表達「可計算」
- ✅ 數學意義明確
- ⚠️ 可能顯得太技術性

### Template vs Expression 深度分析

#### Template 的問題：表達不足

**業界認知偏差**
```java
// 一般人看到 Template，會聯想到簡單替換：
String template = "Hello, {name}!";
String template = "<div>%s</div>";

// 而不會聯想到：
String template = "[YYYY+1]-[MM-1]-[DD]";  // 這有計算！
```

**Template 的既有語義**
- Python string.Template: 純粹替換，無運算
- Java MessageFormat: 格式化，無數學運算
- Mustache: 邏輯最小化，主張「無邏輯模板」

**與專案核心價值不符**
```java
// 如果只是替換，用 String.format 就夠了：
String.format("%d-%02d-%02d", year, month, day);

// 但這個專案的核心價值是「計算」：
"[YYYY+1]-[MM+1]-06"  // 自動處理溢位：12+1 → 隔年1月
"[YYYY]-[MM]-07"      // 與下月6日組成範圍計算
```

**用戶期望落差**
- 看到 `DateTimeTemplate`，用戶可能期望簡單的填空
- 實際使用時才發現「居然還能加減運算！」
- 這個「驚喜」可能變成「困惑」

#### Expression 的優勢

**明確暗示「求值/計算」**
```java
// 業界 Expression 的典型用法：
SpEL:  "#{user.age + 1}"           // Spring Expression Language
JEXL:  "price * quantity * 0.9"    // Java Expression Language
OGNL:  "user.name.toUpperCase()"   // Object-Graph Navigation

// 都強調「計算能力」
```

**符合數學/計算機科學術語**
- Expression（表達式）= 可以被求值的式子
- `[YYYY+1]` 是一個表達式，需要「求值」才能得到結果
- Template（模板）= 固定結構 + 填充內容

**與實際使用案例吻合**
```java
// review.md 中的範例：
LocalDate result = fillWith("[YYYY+1]-12-31", 2020);
// 結果：2021-12-31

// 這本質上是「表達式求值」：
evaluate("[YYYY+1]-12-31", context: {YYYY: 2020})
```

### 最終決定

**採用 `DateTimeExpression`**

**理由總結：**

| 特性 | Template | Expression | Formula |
|-----|----------|------------|---------|
| 表達「計算能力」 | ❌ 弱 | ✅ 強 | ✅ 強 |
| 業界認知度 | ✅ 高 | ✅ 高 | ⚠️ 中 |
| 符合專案核心價值 | ❌ 否 | ✅ 是 | ✅ 是 |
| 與相似框架一致 | ❌ 否 | ✅ 是(SpEL, JEXL) | ⚠️ 較少見 |
| 名稱簡潔度 | ✅ 好 | ✅ 好 | ✅ 好 |

**結論：**
- Template 確實有「表達不足」的問題，無法突出專案的計算特性
- Expression 更準確表達「這是可以求值的表達式」
- 專案的核心價值是「動態計算」而非「靜態填空」

---

## 二、版本號規劃

### 問題
重新命名後，版號應該使用 **0.3.0** 還是從 **0.1.0** 重新開始？

### 決定：使用 0.3.0

**理由：**

#### 1. 符合語意化版本規範 (Semantic Versioning)
- 目前版本：0.2.7-SNAPSHOT
- Breaking Change 在 1.0.0 之前使用 Minor 版本號（0.x.0）
- 0.3.0 清楚表達這是重大改版

#### 2. 體現專案延續性
- 核心功能已經成熟（DateCloze、ClozeFormatRange 等）
- 只是重新命名，不是從頭開發
- 0.3.0 表達「這是第三個里程碑」

#### 3. 符合 review.md 的建議
- review.md 第 821-828 行明確提到：`0.3.0 (Breaking Changes)` 用於重新命名
- 已經規劃好的版本路線：0.3.0 → 0.4.0 → 0.5.0 → 1.0.0

#### 4. 從 0.1.0 重新開始的問題
- 否定了現有的開發成果
- 讓使用者困惑（為什麼版本號倒退？）
- 不符合版本演進邏輯

### 建議的版本描述

```xml
<version>0.3.0-SNAPSHOT</version>
<description>
    0.3.0 版: 重大更新 (Breaking Changes)
        - 專案改名：AkiDateTimeCloze → AkiDateTimeExpression
        - 核心類別改名：DateCloze → DateExpression, ClozeFormatRange → ExpressionFormatRange
        - 工廠方法改善：Factory.create() → of()
        - API 改善：優化參數處理，支援 Builder 模式
        - 新增：支援固定參數、變動 expression 的使用模式
</description>
```

---

## 三、Builder 模式設計問題

### 背景

在改用 Builder 模式時，遇到一個設計問題：
- 有時候是**固定 expression，改動參數**
- 有時候是**固定參數，改動 expression**

### 使用場景分析

#### 場景 A：固定 expression，改動參數（原有功能）

```java
LocalDateExpression expr = new LocalDateExpression("[YYYY]-[MM]-[DD]");

LocalDate date1 = expr.ofYear(2024).ofMonth(6).ofDay(15).resolve();
// 結果：2024-06-15

LocalDate date2 = expr.ofYear(2025).ofMonth(7).ofDay(20).resolve();
// 結果：2025-07-20
```

#### 場景 B：Expression 中部分是固定值

```java
// Expression 中年份是固定的 2024
String expr = "2024-[MM]-[DD]";

// 只需要傳入 month 和 day
LocalDate date = new LocalDateExpression(expr)
    .ofMonth(6)
    .ofDay(15)
    .resolve();  // 結果：2024-06-15

// 問題：如果傳入 ofYear(2025)，應該如何處理？
```

#### 場景 C：固定參數，變動 expression（新需求）

```java
// 建立一個 resolver，固定參數
LocalDateExpression resolver = new LocalDateExpression()
    .ofYear(2024)
    .ofMonth(6)
    .ofDay(15);

// 用不同的 expression 解析
LocalDate date1 = resolver.resolve("[YYYY]-[MM]-[DD]");    // 2024-06-15
LocalDate date2 = resolver.resolve("[YYYY+1]-[MM]-01");    // 2025-06-01
LocalDate date3 = resolver.resolve("2023-[MM]-[DD]");      // 2023-06-15
```

### 實際應用案例

**財報系統：**
```java
// 固定每年 1 月 1 日
"[YYYY]-01-01"  // 只需要年份變數

// 使用：
expr.ofYear(2024).resolve();  // 2024-01-01
expr.ofYear(2025).resolve();  // 2025-01-01
```

**月度報表（查看歷史資料）：**
```java
// 固定 2024 年
"2024-[MM]-01"  // 只需要月份變數

// 使用：
expr.ofMonth(1).resolve();   // 2024-01-01
expr.ofMonth(6).resolve();   // 2024-06-01
```

**週期計算：**
```java
// 全動態
"[YYYY]-[MM]-[DD]"

// 使用：
expr.ofYear(2024).ofMonth(6).ofDay(15).resolve();
```

### 設計考慮：是否必要？

#### ✅ 這是必要的考慮

**理由：**

1. **實際場景確實存在**：上述三種場景都是真實需求
2. **API 易用性問題**：需要明確定義「多餘參數」的行為
3. **錯誤處理**：需要在適當時機驗證必要參數

---

## 四、最終設計方案

### 核心設計：同時支援兩種模式

```java
public class LocalDateExpression {

    private final String defaultExpression;  // 可為 null
    private Integer annualYear;
    private Integer monthOfAnnual;
    private Integer dayOfMonth;

    // ============ 建構方式 ============

    // 建構方式1：固定 expression 模式
    public LocalDateExpression(String expression) {
        this.defaultExpression = expression;
    }

    // 建構方式2：固定參數模式（先不指定 expression）
    public LocalDateExpression() {
        this.defaultExpression = null;
    }

    // ============ 設定參數 ============

    // 設定參數的方法（兩種模式共用）
    public LocalDateExpression ofYear(Integer annualYear) {
        this.annualYear = annualYear;
        return this;
    }

    public LocalDateExpression ofMonth(Integer monthOfAnnual) {
        this.monthOfAnnual = monthOfAnnual;
        return this;
    }

    public LocalDateExpression ofDay(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    // ============ 解析方法 ============

    // 模式1：使用建構時的 expression
    public LocalDate resolve() {
        if (defaultExpression == null) {
            throw new IllegalStateException(
                "No default expression. Use resolve(String expression) instead."
            );
        }
        return resolveWith(defaultExpression);
    }

    // 模式2：傳入新的 expression
    public LocalDate resolve(String expressionStr) {
        return resolveWith(expressionStr);
    }

    // ============ 核心邏輯 ============

    // 核心解析邏輯（兩種模式共用）
    private LocalDate resolveWith(String expressionStr) {
        DateExpression expression = DateExpression.create(expressionStr);
        String strDate = expressionStr;

        // 只處理 expression 實際需要的參數
        if (expression.getIsYearFillable() && annualYear != null) {
            strDate = processYearPart(strDate, expression, annualYear);
        }
        if (expression.getIsMonthFillable() && monthOfAnnual != null) {
            strDate = processMonthPart(strDate, expression, monthOfAnnual);
        }
        if (expression.getIsDayOfMonthFillable() && dayOfMonth != null) {
            strDate = processDayPart(strDate, expression, dayOfMonth);
        }

        // 驗證必要參數是否都提供了
        validateRequiredParameters(expression, expressionStr);

        LocalDate standardDate = DateTimeUtil.Provider.parse2Date(strDate);
        standardDate = standardDate.plusYears(expression.getAddendYears());
        standardDate = standardDate.plusMonths(expression.getAddendMonths());
        standardDate = standardDate.plusDays(expression.getAddendDayOfMonth());

        return standardDate;
    }

    // 驗證必要參數
    private void validateRequiredParameters(DateExpression expression, String expressionStr) {
        List<String> missing = new ArrayList<>();

        if (expression.getIsYearFillable() && annualYear == null) {
            missing.add("year");
        }
        if (expression.getIsMonthFillable() && monthOfAnnual == null) {
            missing.add("month");
        }
        if (expression.getIsDayOfMonthFillable() && dayOfMonth == null) {
            missing.add("day");
        }

        if (!missing.isEmpty()) {
            throw new IllegalStateException(
                String.format(
                    "Missing required parameters %s for expression: %s",
                    missing, expressionStr
                )
            );
        }
    }

    // 清除參數（如果需要重用）
    public LocalDateExpression clear() {
        this.annualYear = null;
        this.monthOfAnnual = null;
        this.dayOfMonth = null;
        return this;
    }

    // ... processYearPart, processMonthPart, processDayPart 保持不變
}
```

### 設計特點

#### 1. 兩個建構子
- `LocalDateExpression(String)` - 固定 expression 模式
- `LocalDateExpression()` - 固定參數模式

#### 2. 兩個 resolve 方法
- `resolve()` - 使用預設 expression
- `resolve(String)` - 傳入新 expression

#### 3. 寬鬆的參數處理
- 允許「過度提供」參數（比如 expression 只需要 month，但提供了 year）
- 系統會自動忽略不需要的參數
- 在 `resolve()` 時驗證必要參數是否缺失

#### 4. 清晰的錯誤訊息
- 如果缺少必要參數，明確指出缺少哪些
- 錯誤訊息包含當前的 expression

### 使用範例

#### 範例 1：固定 expression，改動參數

```java
LocalDateExpression expr = new LocalDateExpression("[YYYY]-[MM]-[DD]");

LocalDate date1 = expr.ofYear(2024).ofMonth(6).ofDay(15).resolve();
// 結果：2024-06-15

LocalDate date2 = expr.ofYear(2025).ofMonth(7).ofDay(20).resolve();
// 結果：2025-07-20

LocalDate date3 = expr.ofYear(2026).ofMonth(12).ofDay(31).resolve();
// 結果：2026-12-31
```

#### 範例 2：固定參數，改動 expression

```java
LocalDateExpression resolver = new LocalDateExpression()
    .ofYear(2024)
    .ofMonth(6)
    .ofDay(15);

LocalDate date1 = resolver.resolve("[YYYY]-[MM]-[DD]");
// 結果：2024-06-15

LocalDate date2 = resolver.resolve("[YYYY+1]-[MM]-01");
// 結果：2025-06-01

LocalDate date3 = resolver.resolve("2023-[MM]-[DD]");
// 結果：2023-06-15 (expression 中的 2023 覆蓋了參數中的 2024)

LocalDate date4 = resolver.resolve("[YYYY]-12-31");
// 結果：2024-12-31 (expression 中的 12 和 31 覆蓋了參數)
```

#### 範例 3：混合使用

```java
// 先固定 expression
LocalDateExpression expr = new LocalDateExpression("[YYYY]-[MM]-01");

// 用預設 expression
LocalDate date1 = expr.ofYear(2024).ofMonth(6).resolve();
// 結果：2024-06-01

// 臨時換個 expression（參數保留）
LocalDate date2 = expr.resolve("[YYYY+1]-[MM]-15");
// 結果：2025-06-15

// 回到預設 expression
LocalDate date3 = expr.resolve();
// 結果：2024-06-01
```

#### 範例 4：自動忽略多餘參數

```java
// Expression 只需要 month
LocalDateExpression expr = new LocalDateExpression("2024-[MM]-01");

// 即使提供了 year 和 day，也會被自動忽略
LocalDate date = expr.ofYear(9999).ofMonth(6).ofDay(99).resolve();
// 結果：2024-06-01（year=9999 和 day=99 被忽略）
```

#### 範例 5：缺少必要參數時的錯誤

```java
LocalDateExpression expr = new LocalDateExpression("[YYYY]-[MM]-01");

// 只提供 month，缺少 year
LocalDate date = expr.ofMonth(6).resolve();
// ❌ IllegalStateException: Missing required parameters [year] for expression: [YYYY]-[MM]-01
```

### 設計原則

#### 1. "Be liberal in what you accept"
- 接受「多餘」的參數，自動忽略
- 不因為提供了不需要的參數而報錯
- 讓使用者可以用統一的模式調用

#### 2. "Fail fast"
- 在 `resolve()` 時立即驗證
- 給出清晰的錯誤訊息
- 幫助使用者快速定位問題

#### 3. "最小驚訝原則"
- Expression 中的固定值優先級高於參數
- 比如：`"2024-[MM]-01"` 中，年份永遠是 2024，即使傳入 `ofYear(2025)`

#### 4. "保持簡單"
- 不需要複雜的工廠方法
- 不需要多個 Builder 類別
- API 使用直觀

---

## 五、優勢總結

### 相比原始設計的改進

#### 原始設計（Cloze + 靜態方法）
```java
LocalDate date = LocalDateCloze.fillWith("[YYYY]-[MM]-[DD]", 2024, 6, 15);
```

**問題：**
- ❌ "Cloze" 命名不直觀
- ❌ 使用 `null` 表示「不填入」：`fillWith(expr, 2024, null, null)`
- ❌ 參數順序固定，不靈活
- ❌ 無法重用配置
- ❌ 不支援固定參數、變動 expression

#### 新設計（Expression + Builder）
```java
LocalDate date = new LocalDateExpression("[YYYY]-[MM]-[DD]")
    .ofYear(2024)
    .ofMonth(6)
    .ofDay(15)
    .resolve();
```

**優勢：**
- ✅ "Expression" 準確表達計算能力
- ✅ 避免 `null` 參數，語義更清晰
- ✅ 參數可選，順序靈活
- ✅ 支援兩種使用模式（固定 expression 或固定參數）
- ✅ 可重用，效能更好
- ✅ IDE 友好（自動補全、鏈式調用）
- ✅ 符合 Java 8+ 的 Fluent API 慣例

### 適用場景對比

| 場景 | 原始設計 | 新設計 |
|-----|---------|--------|
| 一次性計算 | ✅ 簡潔 | ✅ 清晰 |
| 多次計算（同 expression） | ⚠️ 需重複解析 | ✅ 重用實例 |
| 多次計算（同參數） | ❌ 不支援 | ✅ 完美支援 |
| 部分參數可選 | ⚠️ 使用 null | ✅ 不傳入即可 |
| 錯誤提示 | ⚠️ NPE 不明確 | ✅ 清晰的異常訊息 |

---

## 六、後續工作

### 立即執行

- [x] 確定命名：DateTimeExpression
- [x] 確定版本：0.3.0-SNAPSHOT
- [x] 設計 Builder 模式
- [ ] 實作 LocalDateExpression
- [ ] 實作 LocalTimeExpression
- [ ] 實作 LocalDateTimeExpression
- [ ] 更新測試案例
- [ ] 更新文件

### 測試重點

1. **兩種模式測試**
   - 固定 expression，變動參數
   - 固定參數，變動 expression

2. **參數處理測試**
   - 多餘參數被正確忽略
   - 缺少必要參數時正確報錯
   - Expression 固定值覆蓋參數

3. **邊界條件測試**
   - 閏年處理
   - 月份溢位（12+1 → 隔年1月）
   - 日期溢位

4. **錯誤處理測試**
   - 無效 expression 格式
   - 缺少必要參數
   - 無預設 expression 時調用 `resolve()`

### 文件更新

1. **README.md**
   - 專案更名說明
   - 快速開始範例（兩種模式）
   - API 文件連結

2. **JavaDoc**
   - 完善所有公開 API
   - 提供使用範例
   - 說明兩種使用模式

3. **Migration Guide**
   - 從 Cloze 遷移到 Expression
   - 從靜態方法遷移到 Builder
   - Breaking Changes 列表

---

## 七、設計決策記錄

### 為什麼選擇 Expression 而不是 Template？

**核心原因**：專案的核心價值是「動態計算」，而非「靜態填空」。

- `[YYYY+1]` 是需要求值的表達式
- Template 在業界通常指無邏輯的佔位符替換
- Expression 更準確傳達「可計算」的特性

### 為什麼使用 0.3.0 而不是 0.1.0？

**核心原因**：這是改名，不是重寫。

- 核心功能保持不變
- 延續現有的開發歷史
- 符合語意化版本規範

### 為什麼支援兩種使用模式？

**核心原因**：真實場景的需求。

- 有些場景需要固定 expression，改動參數（如：多筆資料的日期計算）
- 有些場景需要固定參數，改動 expression（如：用同一組日期測試多種格式）
- 統一的 API 設計可以同時滿足兩種需求

### 為什麼採用「寬鬆接受參數」策略？

**核心原因**：易用性 > 嚴格性。

- 使用者不需要先檢查 expression 需要哪些參數
- 可以用統一的調用模式
- 在 `resolve()` 時驗證，及早發現錯誤
- 符合 "Be liberal in what you accept" 原則

---

## 八、參考資料

- [Semantic Versioning 2.0.0](https://semver.org/)
- [Fluent Interface - Martin Fowler](https://martinfowler.com/bliki/FluentInterface.html)
- [Builder Pattern - Effective Java](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)
- Spring Expression Language (SpEL) 文件
- Apache Commons JEXL 文件

---

**討論結論**：
1. 專案更名為 `AkiDateTimeExpression`
2. 版本升級至 `0.3.0-SNAPSHOT`
3. 採用 Builder 模式，同時支援兩種使用模式
4. 實作「寬鬆接受參數 + resolve 時驗證」策略

**評估者**：Claude Code
**日期**：2026-01-12
**狀態**：設計方案已確認，待實作
