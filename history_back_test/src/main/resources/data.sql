INSERT INTO supplier
(supplierId, supplierName, supplierNameKana, loginId, password, email) 
VALUES
(1
, 'テスト用サプライヤー'
, 'テストヨウサプライヤー'
, 'su1'
, 'password'
, 'su1@mail.com'),
(2
, 'テスト用サプライヤー2'
, 'テストヨウサプライヤー2'
, 'su2'
, 'password'
, 'su2@mail.com');


INSERT INTO event
(eventId, supplierId, eventName, eventDate, eventDateStart) 
VALUES
(1
, 1
, 'テストイベント1'
, '2021/08/31 13:30:00'
, '2021/08/23 13:00:00'),
(2
, 1
, 'テストイベント2'
, '2021/08/31 14:30:00'
, '2021/08/23 14:00:00');


INSERT INTO consumer
(consumerId, consumerName, consumerNameKana, loginId, password, email, picture) 
VALUES
(1
, 'テスト1'
, 'テストイチ'
, '1'
, 'password'
, '1@mail.com'
, 'img/consumer/1.jpg');


INSERT INTO history
(historyId, eventId, supplierId, consumerId, checkResult, checkDate, picture, faceMaskType, temperature) 
VALUES
(1
, 1
, 1
, 1
, 1
, '2021/10/07 16:18:48'
, 'img/test1.jpg'
, 1
, 36.5),
(2
, 1
, 1
, 1
, 0
, '2021/10/07 16:18:48'
, 'img/test1.jpg'
, 1
, 35.5),
(3
, 2
, 1
, 1
, 0
, NULL
, NULL
, 0
, 35.5),
(4
, 2
, 1
, 1
, 0
, NULL
, NULL
, 0
, 35.5);

INSERT INTO history_error
(historyErrorId, eventId, supplierId, consumerId, correctionFlag, checkResult, checkDate, picture, faceMaskType, temperature) 
VALUES
(1
, 1
, 1
, 1
, 'false'
, 0
, '2021/10/07 16:18:48'
, 'img/test1.jpg'
, 0
, 36.6),
(2
, 1
, 1
, 1
, 'false'
, 0
, '2021/10/07 16:18:48'
, 'img/test1.jpg'
, 0
, 37.5);

INSERT INTO option
(optionId, supplierId, optionGroup, optionName, optionValue) 
VALUES
(1
, 1
, 'slack'
, 'slackUrl'
, 'test.com'),
(2
, 1
, 'slack'
, 'slackUserName'
, 'テスト1'),
(3
, 1
, 'slack'
, 'slackMsg'
, 'テストメッセージ');
