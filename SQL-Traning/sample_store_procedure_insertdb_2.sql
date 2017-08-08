USE payment;

DROP PROCEDURE `add_tr_order`;

CALL `payment`.`add_tr_order`(100);

select count(*) from payment.tr_order;

truncate payment.tr_order;

--------------------------------------
-- Create procedure
--------------------------------------
DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_tr_order`(in num int)
BEGIN

declare i int default 1;
while i <= num do
INSERT INTO `payment`.`tr_order`
(
     `order_id`,
     `ext_transaction_id`,
     `payment_method_name`,
     `payment_method_ref`,
     `service_id`,
     `service_name`,
     `command_id`,
     `command_name`,
     `service_command_id`,
     `initiator_user_id`,
     `initiator_user_type`,
     `initiator_sof_id`,
     `initiator_sof_type_id`,
     `payer_user_id`,
     `payer_user_type`,
     `payer_user_ref_type`,
     `payer_user_ref_value`,
     `payer_sof_id`,
     `payer_sof_type_id`,
     `payee_user_id`,
     `payee_user_type`,
     `payee_user_ref_type`,
     `payee_user_ref_value`,
     `payee_sof_id`,
     `payee_sof_type_id`,
     `currency`,
     `ref_order_id`,
     `amount`,
     `fee`,
     `bonus`,
     `settlement_amount`,
     `product_name`,
     `product_ref1`,
     `product_ref2`,
     `product_ref3`,
     `product_ref4`,
     `product_ref5`,
     `state`,
     `status`,
     `notification_status`,
     `is_deleted`,
     `created_timestamp`,
     `last_updated_timestamp`
)
VALUES
(
	i,
	i,
	'payment_method_name',
	'payment_method_ref',
	i,
	'service_name',
	i,
	'command_name',
	i,
	i,
	'initiator_user_type',
	i,
	i,
	i,
	'payer_user_type',
	'payer_user_ref_type',
	'payer_user_ref_value',
	i,
	i,
	i,
	'payee_user_type',
	'payee_user_ref_type',
	'payee_user_ref_value',
	i,
	i,
	'CUR',
	'ref_order_id',
	i,
	i,
	i,
	i,
	'product_name',
	'product_ref1',
	'product_ref2',
	'product_ref3',
	'product_ref4',
	'product_ref5',
	'state',
	1,
	1,
	1,
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP
);
set i = i + 1;
end while;
END
//

DELIMITER ;
