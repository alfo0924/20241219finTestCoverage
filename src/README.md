# 程式健身社費用計算系統

## 專案說明
此專案實作一個程式健身社的社費計算系統，根據學生的年級、曠課狀況、程式成癮狀況、手指強壯程度和深蹲打字持續時間來計算社費。

## 實作重點

### 主要功能
- 基本社費為500元
- 根據不同條件提供折扣：
    - 曠課次數少於5次折扣50元
    - 打字速度(WPM)分級折扣：60/80/100 WPM分別折扣50/100/150元
    - 深蹲打字持續時間依年級不同提供折扣
    - 程式碼量與複雜度折扣

### 技術實作
1. **介面設計**
    - 實作 `GitHubService` 介面處理程式碼分析
    - 提供 `getLines()` 和 `getWMC()` 方法

2. **單元測試**
    - 使用 JUnit 5 框架
    - 透過 Mockito 模擬 GitHubService
    - 實現完整的分支覆蓋測試

## 測試結果
- Branch Coverage: 96.4%
- Line Coverage: 75%
- Class Coverage: 25%
- Method Coverage: 20%

## 測試案例
- 基本功能測試
- 邊界值測試
- 多重折扣組合測試
- 異常情況處理

## 使用技術
- Java
- JUnit 5
- Mockito
- Jacoco

## 如何執行
1. 使用 Maven 建置專案
```bash
mvn clean install
```

2. 執行測試
```bash
mvn test
```

3. 檢視測試報告
```bash
target/site/jacoco/index.html
```

## 待改進項目
1. 提高分支覆蓋率至100%
2. 增加更多邊界值測試
3. 改善類別和方法的覆蓋率

## 專案結構
```
src/
├── main/java/
│   └── org/example/
│       ├── FeeCalculator.java
│       └── GitHubService.java
├── test/java/
│   └── org/example/
│       └── FeeCalculatorTest.java
└── check.md
```

## 貢獻指南
1. Fork 專案
2. 建立特性分支
3. 提交變更
4. 發送 Pull Request

## 授權
MIT License