create index ak_archive_code on `sample_staff`.`contract` (`archive_code`, `deleted_flag`, `sign_date`);

drop index ak_archive_code on `sample_staff`.`contract`;

SELECT `sample_staff`.`contract`.`archive_code`
FROM `contract`
WHERE 1=1
 AND `contract`.`archive_code` = 'DA970'
 AND `contract`.`deleted_flag` = 0
 AND `contract`.`sign_date` >= '1990-01-01'
;

#######################################

SELECT `contract`.`archive_code`
FROM `contract` use index (`ak_archive_code`)
WHERE 1=1
 AND `contract`.`archive_code` = 'DA970'
 AND `contract`.`deleted_flag` = 0
;
