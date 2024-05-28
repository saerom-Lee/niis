select * from air_product_dts a 
where substr(a.zone_code, 1, 4) = '2014' and substr(a.zone_code, 11, 1) = 'C' 
order by substr(a.zone_code, 1, 4), substr(a.zone_code, 8, 4), lpad(a.ph_course, 4, '0'), lpad(a.ph_num, 4, '0');

select a.*, '', b.*, '', c.*, '', d.*, e.*, f.* from air_product_dts a 
left join air_basemeta_dts b on a.zone_code = b.zone_code and a.ph_course = b.ph_course and a.ph_num = b.ph_num
left join air_orientmap_dts c on a.zone_code = c.zone_code and a.ph_course = c.ph_course and a.ph_num = c.ph_num
left join air_producteo_dts d on a.zone_code = d.zone_code and a.ph_course = d.ph_course and a.ph_num = d.ph_num
left join air_orientmap_as e on a.zone_code = e.zone_code and a.ph_course = e.ph_course and a.ph_num = e.ph_num
left join air_coursetest_dts f on a.zone_code = f.zone_code and a.ph_course = f.ph_course and a.ph_num = f.ph_num
where substr(a.zone_code, 1, 4) = '2017' and substr(a.zone_code, 11, 1) = 'C' 
order by substr(a.zone_code, 1, 4), substr(a.zone_code, 8, 4), lpad(a.ph_course, 4, '0'), lpad(a.ph_num, 4, '0');

select a.year, a.zone, a.cnt as product, b.cnt as basemeata, c.cnt as orientmap, 
d.cnt as map_as, e.cnt as producteo, f.cnt as coursetest, i.cnt as ident, g.cnt as tiff, h.cnt as jpg from (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from air_product_dts 
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) a
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from air_basemeta_dts 
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) b on a.year=b.year and a.zone=b.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from air_orientmap_dts
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) c on a.year=c.year and a.zone=c.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from air_orientmap_as
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) d on a.year=d.year and a.zone=d.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from air_producteo_dts
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) e on a.year=e.year and a.zone=e.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from air_coursetest_dts
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) f on a.year=f.year and a.zone=f.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from sto_airloc_dts
where storage_cde in ('AIR001', 'NIR001')
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) g on a.year=g.year and a.zone=g.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from sto_airloc_dts
where storage_cde in ('AIR009', 'NIR009')
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) h on a.year=h.year and a.zone=h.zone
left join (
select substr(zone_code, 1, 4) as year, substr(zone_code, 8, 4) as zone, count(*) as cnt from META_AIRMAP_IDENT
group by substr(zone_code, 1, 4), substr(zone_code, 8, 4)) i on a.year=i.year and a.zone=i.zone
order by 1, 2