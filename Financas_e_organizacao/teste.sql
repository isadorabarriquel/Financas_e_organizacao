INSERT INTO transacao (
    id,
    descricao,
    tipo,
    valor,
    data,
    usuario_id,
    conta_id,
    categoria_id,
    criado_em,
    atualizado_em
) VALUES (
    '44444444-4444-4444-4444-444444444444',
    'Lanche',
    'DESPESA',  -- ou o tipo que você usa
    50.00,
    NOW() - INTERVAL '30 days',  -- cai no mês anterior
    '11111111-1111-1111-1111-111111111111',
    '22222222-2222-2222-2222-222222222222',
    '33333333-3333-3333-3333-333333333333',
    NOW(),
    NOW()
);
