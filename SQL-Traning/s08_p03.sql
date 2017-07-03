DROP PROCEDURE IF EXISTS `sample_staff`.INS_USER_STAT;

DELIMITER //
CREATE PROCEDURE `sample_staff`.INS_USER_STAT(
	in_user_id INT(11)
)
BEGIN
	INSERT INTO `sample_staff`.`user_stat`(
		`user_id`,
		`date`,
		`hour`,
		`login_count`,
		`insert_dt`
	)

    SELECT
		`user_id`,
		DATE(`user_login`.`login_dt`) as `login_date`,
		HOUR(`user_login`.`login_dt`) as `login_hour`,
		COUNT(*) as `login_count`,
        NOW() as `insert_dt`
	FROM `sample_staff`.`user_login`
    WHERE 1=1
		AND `user_id` = in_user_id
    GROUP BY
		`login_date`,
        `login_hour`,
        `user_id`;
END
;
//
DELIMITER ;


CALL `sample_staff`.INS_USER_STAT(1);
