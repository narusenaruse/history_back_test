-- Project Name : face
-- Date/Time    : 2021/08/23 13:25:23
-- Author       : NT-190013
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- オプション
drop table if exists option cascade;

create table option (
  optionId numeric not null
  , supplierId numeric not null
  , optionGroup character varying(16) not null
  , optionName character varying(16)
  , optionValue character varying(100)
  , constraint option_PKC primary key (optionId,supplierId)
) ;

-- 履歴_認証失敗
drop table if exists history_error cascade;

create table history_error (
  historyErrorId numeric not null
  , eventId numeric not null
  , supplierId numeric not null
  , consumerId numeric default null
  , correctionFlag boolean default false not null
  , checkResult integer default 0 not null
  , checkDate timestamp not null
  , picture character varying(100) not null
  , faceMaskType integer not null
  , temperature double precision not null
  , constraint history_error_PKC primary key (historyErrorId,eventId,supplierId)
) ;

-- 履歴
drop table if exists history cascade;

create table history (
  historyId numeric not null
  , eventId numeric not null
  , supplierId numeric not null
  , consumerId numeric not null
  , checkResult integer default 0 not null
  , checkDate timestamp default null
  , picture character varying(100) default null
  , faceMaskType integer
  , temperature double precision
  , constraint history_PKC primary key (historyId,eventId,supplierId,consumerId)
) ;

-- イベント
drop table if exists event cascade;

create table event (
  eventId numeric not null
  , supplierId numeric not null
  , eventName character varying(30) not null
  , eventDate timestamp not null
  , eventDateStart timestamp not null
  , constraint event_PKC primary key (eventId,supplierId)
) ;

-- サプライヤー
drop table if exists supplier cascade;

create table supplier (
  supplierId numeric not null
  , supplierName character varying(30) not null
  , supplierNameKana character varying(30) not null
  , loginId character varying(16) not null
  , password character varying(16) not null
  , email character varying(100) not null
  , constraint supplier_PKC primary key (supplierId)
) ;

-- コンシューマー
drop table if exists consumer cascade;

create table consumer (
  consumerId numeric not null
  , consumerName character varying(30) not null
  , consumerNameKana character varying(30) not null
  , loginId character varying(16) not null
  , password character varying(16) not null
  , email character varying(100) not null
  , picture character varying(100) not null
  , constraint consumer_PKC primary key (consumerId)
) ;

alter table option
  add constraint option_FK1 foreign key (supplierId) references supplier(supplierId);

alter table history_error
  add constraint history_error_FK1 foreign key (consumerId) references consumer(consumerId);

alter table history_error
  add constraint history_error_FK2 foreign key (eventId,supplierId) references event(eventId,supplierId);

alter table history
  add constraint history_FK1 foreign key (consumerId) references consumer(consumerId);

alter table history
  add constraint history_FK2 foreign key (eventId,supplierId) references event(eventId,supplierId);

alter table event
  add constraint event_FK1 foreign key (supplierId) references supplier(supplierId);

comment on table option is 'オプション';
comment on column option.optionId is 'オプションID';
comment on column option.supplierId is 'サプライヤーID';
comment on column option.optionGroup is '設定グループ';
comment on column option.optionName is '設定名';
comment on column option.optionValue is '設定値';

comment on table history_error is '履歴_認証失敗';
comment on column history_error.historyErrorId is '履歴_認証失敗ID';
comment on column history_error.eventId is 'イベントID';
comment on column history_error.supplierId is 'サプライヤーID';
comment on column history_error.consumerId is 'コンシューマーID';
comment on column history_error.correctionFlag is '手動補正フラグ';
comment on column history_error.checkResult is '判定結果:判定結果(0:エラー, 1:手動でOKに変更)';
comment on column history_error.checkDate is '認証時間';
comment on column history_error.picture is '顔写真';
comment on column history_error.faceMaskType is 'マスク着用タイプ';
comment on column history_error.temperature is '体温';

comment on table history is '履歴';
comment on column history.historyId is '履歴ID';
comment on column history.eventId is 'イベントID';
comment on column history.supplierId is 'サプライヤーID';
comment on column history.consumerId is 'コンシューマーID';
comment on column history.checkResult is '判定結果:判定結果(0:認証待ち, 1:認証OK)';
comment on column history.checkDate is '認証時間';
comment on column history.picture is '顔写真';
comment on column history.faceMaskType is 'マスク着用タイプ';
comment on column history.temperature is '体温';

comment on table event is 'イベント';
comment on column event.eventId is 'イベントID';
comment on column event.supplierId is 'サプライヤーID';
comment on column event.eventName is 'イベント名';
comment on column event.eventDate is '開催日時';
comment on column event.eventDateStart is '入場開始時間';

comment on table supplier is 'サプライヤー';
comment on column supplier.supplierId is 'サプライヤーID';
comment on column supplier.supplierName is '社名';
comment on column supplier.supplierNameKana is '社名カナ';
comment on column supplier.loginId is 'ログインID';
comment on column supplier.password is 'パスワード';
comment on column supplier.email is 'メールアドレス';

comment on table consumer is 'コンシューマー';
comment on column consumer.consumerId is 'コンシューマーID';
comment on column consumer.consumerName is '氏名';
comment on column consumer.consumerNameKana is '氏名カナ';
comment on column consumer.loginId is 'ログインID';
comment on column consumer.password is 'パスワード';
comment on column consumer.email is 'メールアドレス';
comment on column consumer.picture is '顔写真';

drop view if exists analysis_view cascade;
create view analysis_view as
select eventId
, supplierId
, consumerId
, CASE checkResult
    -- OK
	WHEN 1 THEN 1
	-- 未判定
	WHEN 0 THEN 0
  END as checkResult
, checkDate
, faceMaskType
, temperature
from history
union all
select eventId
, supplierId
, consumerId
, CASE checkResult
    -- OK
	WHEN 1 THEN 1
	-- エラー
	WHEN 0 THEN 2
  END
, checkDate
, faceMaskType
, temperature
from history_error;

drop view if exists history_view cascade;
create view history_view as
select historyId
, history.eventId
, history.supplierId
, history.consumerId
, consumer.consumerName
, consumer.consumerNameKana
, history.checkResult
, history.checkDate
, history.picture
from history
left outer join consumer
on history.consumerId = consumer.consumerId;

drop SEQUENCE if exists selectOptionId cascade;
CREATE SEQUENCE selectOptionId
INCREMENT BY 1
START WITH 3
NO CYCLE;
