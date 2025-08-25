<h1>專案說明</h1>
<pre>
這是一個POS營運銷售系統管理，主要功能包括：

1. POS作業系統: 新增訂單，員工下訂(需是會員)，員工協助下訂(會員、非會員皆可)
2. 訂單管理：修改、刪除及查詢訂單。
3. 員工資料管理：管理員工帳戶與權限設定(admin開放所有權限，員工僅查詢)。
4. 會員資料管理：處理客戶資料維護(更新資料、刪除)。
5. 報表圖表管理：管理相關報表。
</pre>

<h1>程式設計</h1>
<pre>
  系統採用 MVC（Model-View-Controller） 設計模式，以確保架構清晰、可維護性高。
  此外，系統實作 DAO（Data Access Object）層 負責數據存取，並透過 Service 層 處理業務邏輯。
  後端使用 MySQL 作為資料庫，確保數據管理的穩定性與效率。


</pre>


<h1>目錄結構</h1>
<pre>
└─java
   ├─controller
   │  ├─employee
   │  │  ├─AddEmployee.java
   │  │  └─UpdateEmployeeUI.java
   │  ├─member
   │  │  ├─AddMember.java
   │  │  ├─UpdateMemberUI.java
   │  │  └─selectMemberUI.java
   │  ├─porder
   │  │  ├─ConfirmUI.java
   │  │  ├─Finished.java
   │  │  ├─Manager.java
   │  │  ├─PosUI.java
   │  │  ├─QuantityUI.java
   │  │  ├─Report.java
   │  │  ├─RoundedButton.java
   │  │  └─ShowPorderDetail.java
   │  └─Login.java
   ├─dao
   │  ├─impl
   │  │  ├─EmployeeDaoImpl.java
   │  │  ├─MemberDaoImpl.java
   │  │  ├─PorderDaoImpl.java
   │  │  ├─Porder_DetailDaoImpl.java
   │  │  └─ProductDaoImpl.java
   │  ├─EmployeeDao.java
   │  ├─MemberDao.java
   │  ├─PorderDao.java
   │  ├─Porder_DetailDao.java
   │  └─ProductDao.java
   ├─model
   │  ├─Category.java
   │  ├─Employee.java
   │  ├─Member.java
   │  ├─Porder.java
   │  ├─Porder_Detail.java
   │  └─Product.java
   ├─service
   │  ├─impl
   │  │  ├─EmployeeServiceImpl.java
   │  │  ├─MemberServiceImpl.java
   │  │  ├─PorderServiceImpl.java
   │  │  └─ProductServiceImpl.java
   │  ├─EmployeeService.java
   │  ├─MemberService.java
   │  ├─PorderService.java
   │  └─ProductService.java
   └─util
      ├─CreateExecl.java
      ├─DbConnection.java
      ├─LocalDateTimeEx.java
      ├─Session.java
      ├─Tool.java
      └─auto_numbering.java

  src 目錄檔案說明如下:

  📂 controller（控制層 - 負責 UI 及業務邏輯）
  這部分負責與使用者互動，使用 JFrame、JDialog 來設計介面，並與 Service 層 互動。

  *Login （登入介面）程式進入點

  📁 member（會員管理）
  AddMember.java → 會員註冊
  selectMemberUI.java → 員工點餐時，可選擇是否加入會員資料
  UpdateMemberUI.java →會員基本資料維護

  📁 employ（員工管理）
  AddEmployee.java → 新增員工(僅admin權限開放)
  UpdateEmployeeUI.java → 員工基本資料維護及權限設定

  📂 dao（資料存取層 - 直接與 MySQL 互動）
  負責對 MySQL 進行 CRUD（新增、查詢、更新、刪除） 操作。
  EmployDao.java → 員工資料存取介面
  MemberDao.java → 會員資料存取介面
  PorderDao.java → 訂單資料存取介面
  Porder_DetailDao.java → 訂單摘要查詢
  ProductDao.java → 產品資料存取介面

  📁 impl（DAO 具體實作）
  EmployDaoImpl.java → 員工 DAO 實作
  MemberDaoImpl.java → 會員 DAO 實作
  PorderDaoImpl.java → 訂單 DAO 實作
  Porder_DetailDaoImpl.java → 訂單摘要 DAO 實作
  ProductDaoImpl.java → 產品 DAO 實作

  📂 model（數據模型 - 定義 POJO 類別）
  對應 MySQL 的資料表，每個類別代表一個 表的結構。
  Employ.java → 員工類別（對應 employ 資料表）
  Member.java → 會員類別（對應 member 資料表）
  Porder.java → 訂單類別（對應 porder 資料表）
  PorderDetail.java → 訂單明細類別（對應porder_detail資料表）
  Product.java → 產品類別（對應 product 資料表）
  Category.java →品項類別（對應 category資料表）

  📂 service（業務邏輯層 - 處理 DAO 操作）
  負責調用 DAO，執行 交易管理（Transaction Management） 及 商業邏輯。
  EmployService.java → 員工業務邏輯
  MemberService.java → 會員業務邏輯
  PorderService.java → 訂單業務邏輯
  ProductService.java → 產品業務邏輯
  
  📁 impl（Service 具體實作）
  EmployServiceImpl.java → 員工 Service 實作
  MemberServiceImpl.java → 會員 Service 實作
  PorderServiceImpl.java → 訂單 Service 實作
  ProductServiceImpl.java → 產品 Service 實作

  📂 util（工具類別 - 提供共用函式）
  DbConnection.java → MySQL 連線工具類
  Tool.java → 其他工具函式，例如 檔案存取、格式轉換
  auto_numbering.java → 訂單編號，員工編號，會員編號(自動跳號)
  CreateExecl.java → 報表製作
  LocalDateTimeEx.java →時間取得
  Session.java →紀錄登錄時的資料(會員或員工)

  📌 總結
  這個 MVC 架構 的系統包含：
  Controller（UI 介面） → controller 資料夾（使用 JFrame 來顯示介面）。
  Model（資料模型） → model 資料夾（定義對應 MySQL 的類別）。
  DAO（資料存取層） → dao 資料夾（負責 CRUD 操作）。
  Service（業務邏輯層） → service 資料夾（處理 DAO 操作及交易管理）。
  Util（工具類） → util 資料夾（提供 MySQL 連線與工具函式）。











</pre>
