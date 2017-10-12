LOCK TABLES `user_tb` WRITE;
LOCK TABLES `office_tb` WRITE;
LOCK TABLES `room_tb` WRITE;
LOCK TABLES `reservation_tb` WRITE;
LOCK TABLES `doorlock_tb` WRITE;
LOCK TABLES `coupon_tb` WRITE;
LOCK TABLES `user_coupone_tb` WRITE;
LOCK TABLES `user_key_tb` WRITE;


/*!40000 ALTER TABLE `user_tb` DISABLE KEYS */;
INSERT INTO `user_tb` VALUES ('wlsgk0323','LimJinHa','*A4B6157319038724E3560894F7F932C8886EBFCF','01047019413','TestToken');
/*!40000 ALTER TABLE `user_tb` ENABLE KEYS */;
UNLOCK TABLES;


	/*!40000 ALTER TABLE `office_tb` DISABLE KEYS */;
	INSERT INTO `office_tb` VALUES ('certis','certis','security circle of PKNU','051-987-6543'),('wap','wap','Programming circle of PKNU','051-123-4567');
	/*!40000 ALTER TABLE `office_tb` ENABLE KEYS */;
	UNLOCK TABLES;
	
	
	/*!40000 ALTER TABLE `room_tb` DISABLE KEYS */;
	INSERT INTO `room_tb` VALUES ('certis','001',2,3),('certis','002',2,4),('wap','001',2,4),('wap','002',2,4),('wap','003',4,6);
	/*!40000 ALTER TABLE `room_tb` ENABLE KEYS */;
	UNLOCK TABLES;
	


	
	/*!40000 ALTER TABLE `reservation_tb` DISABLE KEYS */;
	INSERT INTO `reservation_tb` VALUES (7,'wlsgk0323','2017-08-19','2017-08-21','wap','001'),(8,'wlsgk0323','2017-08-25','2017-08-28','certis','002');
	/*!40000 ALTER TABLE `reservation_tb` ENABLE KEYS */;
	UNLOCK TABLES;
	
	
	
	/*!40000 ALTER TABLE `doorlock_tb` DISABLE KEYS */;
	INSERT INTO `doorlock_tb` VALUES ('certis','001','172.169.1.1'),('certis','002','172.169.1.2'),('wap','001','172.168.0.1'),('wap','002','172.168.0.2'),('wap','003','172.168.0.3');
	/*!40000 ALTER TABLE `doorlock_tb` ENABLE KEYS */;
	UNLOCK TABLES;
	

	
	/*!40000 ALTER TABLE `coupon_tb` DISABLE KEYS */;
	INSERT INTO `coupon_tb` VALUES (1,'5000won','2017-08-01','2017-08-31','5000won discount',5000),(2,'10000won','2017-08-01','2017-08-15','10000won discount',10000);
	/*!40000 ALTER TABLE `coupon_tb` ENABLE KEYS */;
	UNLOCK TABLES;

	

	
	/*!40000 ALTER TABLE `user_coupone_tb` DISABLE KEYS */;
	INSERT INTO `user_coupone_tb` VALUES ('wlsgk0323',1),('wlsgk0323',2);
	/*!40000 ALTER TABLE `user_coupone_tb` ENABLE KEYS */;
	UNLOCK TABLES;
	


	
	/*!40000 ALTER TABLE `user_key_tb` DISABLE KEYS */;
	INSERT INTO `user_key_tb` VALUES (7,'wlsgk0323'),(8,'wlsgk0323');
	/*!40000 ALTER TABLE `user_key_tb` ENABLE KEYS */;
	UNLOCK TABLES;
	

	

	
	