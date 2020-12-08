CREATE TABLE account(
	id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
	bankNo VARCHAR(20) COMMENT '银行卡号',
	username VARCHAR(40) COMMENT '用户名',
	money FLOAT COMMENT '账户余额'
)
UPDATE account SET money=money-200 WHERE bankNo='110'
UPDATE account SET money=money+'200' WHERE bankNo='911'

#开启事务：start transaction;
#回滚:rollback;(回滚必须在开启事务之后，提交事务之前才有效)
#提交事务：commit;

#事务：当一个业务有多个操作时，这多个操作要么全部成功要么全部失败，满足这一特性就称之为事务
	
	#原子性(automic)：多个操作不能够分割，是一个整体
	
	#一致性(consistent)：事务操作前与操作后，数据最终的状态一样的
	
	#隔离性(isolation)：多个事务之间互不干扰，隔离有4中级别：read uncommitted(读取未提交)、read committed(读取提交)、repeatable read(重复读)、serializable(序列)
		
		#脏读：A事务可以读取了B事务还未提交的数据
			#当mysql软件的隔离级别为read uncommitted的时候，会引发脏读
			#查看当前软件的隔离级别：select @@tx_isolation;
			#修改MySQL的隔离级别：set global transaction isolation level 级别名；
			#将隔离级别设置为:read committed;
		
		#虚读(幻读)：多次读取的数据记录条数不一样
		
		#不可重复读：多次读取的数据结果不一样
	
	#持久性(durable)：如果数据最终进入到数据库中后，数据就永久保存
	