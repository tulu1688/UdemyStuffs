
use customer;

--------------------------------------
-- Create procedure
--------------------------------------
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `customer`.`add_customer`(in num int)
BEGIN

declare i int default 0;
while i < num do
INSERT INTO `customer`.`customer`
(
`mobile_number`,
`unique_reference`,
`firstname`,
`lastname`,
`place_of_birth`,
`date_of_birth`,
`citizen_card_id`,
`passport_id`,
`citizen_card_date_of_issue`,
`passport_date_of_issue`,
`citizen_card_place_of_issue`,
`passport_place_of_issue`,
`permanent_address`,
`current_address`,
`email`,
`occupations`,
`nationality`,
`beneficiary`,
`kyc_status`,
`kyc_remark`,
`kyc_updated_timestamp`,
`is_suspended`,
`created_timestamp`,
`last_updated_timestamp`,
`is_deleted`)
VALUES
(
i,
i,
concat('firstname',i),
concat('lastname',i),
'Vietnam',
CURRENT_TIMESTAMP,
'citizen_card_id',
'passport_id',
CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP,
'citizen_card_place_of_issue',
'passport_place_of_issue',
'permanent_address',
'current_address',
'email@email.email',
'occupations',
'Vietnam',
'beneficiary',
1,
'remark',
CURRENT_TIMESTAMP,
0,
CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP,
0);

set i = i + 1;
end while;
END
//

DELIMITER ;
