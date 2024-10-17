#language: pt
@run
Funcionalidade: Busca de vagas QA no site da NTTdata

  Cenário: ID1_Buscar vaga de QA pelo site NTT
    Dado que o usuário navega para o site do Google
    Quando o usuário pesquisa por vagas QA no site da NTT
    Então deverá ser possível visualizar a vaga QA e seu título