INSERT INTO public.forma_pagamento(descricao)VALUES ('Dinheiro');
INSERT INTO public.forma_pagamento(descricao)VALUES ('Pix');
INSERT INTO public.forma_pagamento(descricao)VALUES ('Cheque');

insert into public.produto(descricao, precocompra, precovenda) VALUES ('Estilete afiado', 7.80, 10.50);

INSERT INTO public.cliente(nome, cpf, rg) VALUES ('Nelson Jucoski', 78768721900, 2671994);
INSERT INTO public.cliente(nome, cpf, rg) VALUES ('Esther Oliveira', 78768721901, 2631994);
INSERT INTO public.cliente(nome, cpf, rg) VALUES ('Cristian Jucoski', 98768721900, 5671994);
INSERT INTO public.cliente(nome, cpf, rg) VALUES ('Leticia Jucoski', 48768721900, 7671994);

INSERT INTO public.pedido(id_cliente, id_forma_pagamento)VALUES ( 1, 1);
INSERT INTO public.itens_pedido(quantidade, valor_item, id_pedido, id_produto) VALUES ( 2, 28.88, 1, 1);
