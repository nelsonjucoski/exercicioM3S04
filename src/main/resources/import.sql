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

INSERT INTO public.role(nome_role) VALUES ('ROLE_ADMIN');
INSERT INTO public.role(nome_role) VALUES ('ROLE_CAIXA');

INSERT INTO public.usuario(login, nome, senha) VALUES ('tilico', 'Nelson jucoski', '$2a$10$8okjbHGqFzdFAX.XtVBO6eEZWi4F7W285Yy6ZsQC9ZyecUB.gO91C'); -- 102030
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (1, 1);

INSERT INTO public.usuario(login, nome, senha) VALUES ('esther', 'Esther Oliveira da Silva', '$2a$10$umA2cp3zGaSMPAGreI3PKePL9eZaDcravDUIONL./dbk1g.LQS7hW'); -- 203040
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (2, 2);
