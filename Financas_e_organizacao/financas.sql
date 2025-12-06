select * from transacao
select * from conta;
select * from categoria;
select co.*, c.id categoria, u.id usuario from categoria c 
join conta co on c.usuario_id = co.usuario_id
join usuario u on u.id = co.usuario_id
where c.usuario_id = '70580525-6e0f-416e-b240-a86572043c8e'