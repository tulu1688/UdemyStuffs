set @row_number := 0;

select
	`date`,
    `hour`,
    `user_id`,
    `login_count`,
    `row_number`
from
(
select
	`date`,
	`hour`,
    `user_id`,
    `login_count`,
    @row_number := ifnull(@row_number, 1) + 1 as `row_number`
from
	`sample_staff`.`user_stat`
group by
	`date`,
    `hour`,
    `user_id`
order by
	`login_count` desc
) as xTMP
where
	`row_number` <= 3
;
