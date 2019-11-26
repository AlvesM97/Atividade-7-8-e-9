<?php
//CONECTAR AO BANCO!
$servidor = "192.168.30.70:3307";
$usuario = "crm_admin";
$senha = "senai";
$banco = "crm";
	

$pdo = new PDO("mysql:host=$servidor;dbname=$banco", "$usuario", "$senha"); 
$pdo->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
$conexao = mysqli_connect($servidor, $usuario,
	$senha, $banco);
if(!$conexao){
	die(mysqli_error($conexao));
}
//se o comando foi enviado...
$comando = verificaValorPost("comando");
if($comando === "teste"){
	// se o valor foi setado
	$valor = verificaValorPost('valor');
	//enviar multiplicado de volta para o android
	$multiplicado = (floatval($valor)) * 2;
	echo($multiplicado);
}
else if($comando == 'inserir'){
	$cpf = verificaValorPost('cpf');
	$nome = verificaValorPost('nome');
	$endereco = verificaValorPost('endereco');
	$telefone = verificaValorPost('telefone');
	$valor = verificaValorPost('valor');
	
	$multiplicado = (floatval($valor)) * 2;
	
	$data = [
		[$cpf, $nome, $endereco, $telefone, $multiplicado]
	];
	$sql = "INSERT INTO cliente
		(cpf,nome,endereco,telefone,valor) VALUES (?,?,?,?,?)";
	$stmt= $pdo->prepare($sql);
	try {
		$pdo->beginTransaction();
		foreach ($data as $row)
		{
			$stmt->execute($row);
		}
		$pdo->commit();
		echo("insert ok");
	}catch (Exception $e){
		$pdo->rollback();
		echo("insert erro ".$e->getCode());
		switch($e->getCode()){
			case 23000:
				echo(" CPF já existente!");
			break;
			case 22001:
				echo(" Algum campo excedeu o limite de caracteres");
			break;
		}
		//throw $e->getCode();
	}
}

else if($comando == "buscarNome"){
	$nome = verificaValorPost('valor');
	$stmt = $pdo->prepare("SELECT * FROM crm.cliente WHERE UPPER (nome) LIKE '%".$nome."%' LIMIT 10 "); 

	$stmt->execute();
	$registros = array();
	while ($row = $stmt->fetch()) {
		$linha2 = array(
			'nome'=>$row['nome'], 
			'cpf'=>$row['cpf'],
			'endereco' =>$row['endereco'],
			'telefone'=>$row['telefone'],
			'valor'=>$row['valor']);
		array_push($registros, $linha2);
	}
	echo json_encode($registros);
}

function verificaValorPost($var){
	if(!isset($_POST["$var"])){
		echo("Faltou o $var");
	}
	return $_POST["$var"];
}


?>