select @avg_company_salary := avg(`salary`.`salary_amount`)
from `sample_staff`.`salary`;

select
	`year_month`,
    `department_id`,
    `department_name`,
    `department_average_salary`,
    @avg_company_salary as `comany_average_salary`,
    case
		when `department_average_salary` < @avg_company_salary then "lower"
        when `department_average_salary` > @avg_company_salary then "higher"
        else "same"
    end as `department_vs_company`
from
(
select
	date_format(`salary`.`from_date`, '%m_%Y') as `year_month`,
    `department`.`id` as `department_id`,
    `department`.`name` as `department_name`,
	@avg_department_salary := avg(`salary`.`salary_amount`) as `department_average_salary`
from `sample_staff`.`salary`
inner join `employee` on 1=1
	and `employee`.`id` = `salary`.`employee_id`
inner join `department_employee_rel` on 1=1
	and `department_employee_rel`.`employee_id` = `employee`.`id`
inner join `department` on 1=1
	and `department`.`id` = `department_employee_rel`.`department_id`

where 1=1
	and `salary`.`deleted_flag` = 0

group by
	`department_id`,
	`year_month`
) xTMP;
