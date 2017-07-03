insert into bi_data.valid_offers (
  `offer_id`,
  `hotel_id`,
  `price_usd`,
  `original_price`,
  `original_currency_code`,
  `checkin_date`,
  `checkout_date`,
  `breakfast_included_flag`,
  `valid_from_date`,
  `valid_to_date`
)
select
  `enterprise_offer_cleanse`.`id` AS `offer_id`,
  `enterprise_offer_cleanse`.`hotel_id` AS `hotel_id`,
  `enterprise_offer_cleanse`.`sellings_price` as `price_usd`,
  `primary_lst_currency`.`code` AS `original_currency_code`,
  `enterprise_offer_cleanse`.`checkin_date`,
  `enterprise_offer_cleanse`.`checkout_date`,
  `enterprise_offer_cleanse`.`breakfast_included_flag`,
  `enterprise_offer_cleanse`.`offer_valid_from` AS `valid_from_date`,
  `enterprise_offer_cleanse`.`offer_valid_to` AS `valid_to_date`
from
  enterprise_data.offer_cleanse_date_fix `enterprise_offer_cleanse`,
  primary_data.lst_currency `primary_lst_currency`
where 1=1
  AND `enterprise_offer_cleanse`.`currency_id`=1
  AND `primary_lst_currency`.`id`=1
;
