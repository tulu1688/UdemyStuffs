DROP PROCEDURE IF EXISTS `sample_staff`.INS_USER_LOGIN_DATA_GENERATOR;

DELIMITER //
CREATE PROCEDURE `sample_staff`.INS_USER_LOGIN_DATA_GENERATOR()
BEGIN
	INSERT INTO `sample_staff`.`user_login` (
		`user_id`,
		`login_dt`,
        `ip_address`,
        `insert_dt`,
        `insert_process_code`
	)
    SELECT
		`user`.`id` as `user_id`,
        NOW() as `login_dt`,
        INET_ATON(CONCAT('10.52.',floor(rand() * 255),'.', floor(rand() * 255))) as `ip_address`,
        NOW() as `insert_dt`,
        'INS_USER_LOGIN_DATA_GENERATOR' as `insert_process_code`
    FROM `sample_staff`.`user`
    ORDER BY RAND()
    LIMIT 100
    ;
END;
//
DELIMITER ;

CALL `sample_staff`.INS_USER_LOGIN_DATA_GENERATOR();

DROP EVENT IF EXISTS `sample_staff`.`ENV_INSERT_LOGIN_DATA`;

DELIMITER //
CREATE EVENT `sample_staff`.`ENV_INSERT_LOGIN_DATA`
ON SCHEDULE EVERY 30 SECOND
	STARTS NOW()
    ENDS NOW() + INTERVAL 1 HOUR
DO CALL `sample_staff`.INS_USER_LOGIN_DATA_GENERATOR();
//
DELIMITER ;
