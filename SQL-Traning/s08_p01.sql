## Using Views
DROP VIEW IF EXISTS `sample_staff`.`v_average_salary`;

CREATE VIEW `sample_staff`.`v_average_salary` AS
SELECT
	`department`.`id` as `department_id`,
    `department`.`name` as `department_name`,
    avg(`salary`.`salary_amount`) as `average_salary_amount`,
    date_format(`salary`.`from_date`, '%m_%Y') as `month_year`
FROM
	`sample_staff`.`department`
INNER JOIN `sample_staff`.`department_employee_rel` ON 1=1
	AND `sample_staff`.`department`.`id` = `sample_staff`.`department_employee_rel`.`department_id`
INNER JOIN `sample_staff`.`employee` ON 1=1
	AND `sample_staff`.`department_employee_rel`.`employee_id` = `sample_staff`.`employee`.`id`
INNER JOIN `sample_staff`.`salary` ON 1=1
	AND `sample_staff`.`salary`.`employee_id` = `sample_staff`.`employee`.`id`

WHERE
	`salary`.`from_date` < '1990-01-01'

GROUP BY
	`month_year`,
    `department_id`
;


select
	`department_id`,
    `department_name`,
    `average_salary_amount`,
    `month_year`
from
	`sample_staff`.`v_average_salary`
where `department_id` = 3;


## Using functions
DROP PROCEDURE IF EXISTS `sample_staff`.GET_DEPARTMENT_AVERAGE_SALARY

DELIMITER //
CREATE PROCEDURE `sample_staff`.`GET_DEPARTMENT_AVERAGE_SALARY`(
	in_depart_id INT
)
BEGIN
	SELECT
		`department`.`id` as `department_id`,
		`department`.`name` as `department_name`,
		avg(`salary`.`salary_amount`) as `average_salary_amount`,
		date_format(`salary`.`from_date`, '%m_%Y') as `month_year`
	FROM
		`sample_staff`.`department`
	INNER JOIN `sample_staff`.`department_employee_rel` ON 1=1
		AND `sample_staff`.`department`.`id` = `sample_staff`.`department_employee_rel`.`department_id`
	INNER JOIN `sample_staff`.`employee` ON 1=1
		AND `sample_staff`.`department_employee_rel`.`employee_id` = `sample_staff`.`employee`.`id`
	INNER JOIN `sample_staff`.`salary` ON 1=1
		AND `sample_staff`.`salary`.`employee_id` = `sample_staff`.`employee`.`id`

	WHERE 1=1
		AND `salary`.`from_date` < '1990-01-01'
        AND `department_id` = `in_depart_id`

	GROUP BY
		`month_year`,
		`department_id`;
END;
//
DELIMITER ;


## Execute them
SELECT * FROM
	sample_staff.v_average_salary
WHERE
	department_id = 2
;

CALL `sample_staff`.GET_DEPARTMENT_AVERAGE_SALARY(1)
;
