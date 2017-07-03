#Create a new table sample_staff.invoice_partitioned based on sample_staff.invoice, but change the following:
# - and add one more column: department_code
# - remove the current partitions & sub-partitions

show create table `sample_staff`.`invoice`;

CREATE TABLE `sample_staff`.`invoice_partitioned` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) unsigned NOT NULL,
  `invoiced_date` date NOT NULL,
  `paid_flag` tinyint(4) NOT NULL DEFAULT '0',
  `insert_dt` datetime NOT NULL,
  `insert_user_id` int(11) NOT NULL DEFAULT '-1',
  `insert_process_code` varchar(255) DEFAULT NULL,
  `update_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user_id` int(11) NOT NULL DEFAULT '-1',
  `update_process_code` varchar(255) DEFAULT NULL,
  `deleted_flag` tinyint(4) NOT NULL DEFAULT '0',
  `department_code` varchar(60) default null,
  PRIMARY KEY (`id`,`invoiced_date`),
  KEY `idx_employee_id` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=973489 DEFAULT CHARSET=utf8
;

# Show all department
select `sample_staff`.`department`.code from `department`;


# Copy all datat from `sample_staff`.`invoice` to `sample_staff`.`invoice_partitioned`
insert into `sample_staff`.`invoice_partitioned` (
	`employee_id`,
    `invoiced_date`,
    `paid_flag`,
    `insert_dt`,
    `insert_user_id`,
    `insert_process_code`,
    `update_dt`,
    `update_user_id`,
    `update_process_code`,
    `deleted_flag`,
    `department_code`
)
select
	`invoice`.`employee_id`,
    `invoice`.`invoiced_date`,
    `invoice`.`paid_flag`,
    `invoice`.`insert_dt`,
    `invoice`.`insert_user_id`,
    `invoice`.`insert_process_code`,
    `invoice`.`update_dt`,
    `invoice`.`update_user_id`,
    `invoice`.`update_process_code`,
    `invoice`.`deleted_flag`,
    `department`.`code` AS `department_code`
from `invoice`
inner join `sample_staff`.`department_employee_rel` on 1=1
	and `invoice`.`employee_id` = `department_employee_rel`.`employee_id`
    and `department_employee_rel`.`deleted_flag` = 0
inner join `sample_staff`.`department` on 1=1
	and `department_employee_rel`.`department_id` = `department`.`id`
;
