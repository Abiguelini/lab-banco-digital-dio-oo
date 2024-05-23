import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// scanner
		Scanner leitor = new Scanner(System.in);

		// banco instanciado
		Banco banco = new Banco();

		System.out.println("BEM VINDO AO BANCO: " + banco);
		System.out.println();

		while (true) {
			System.out.println("Digite a opçao que deseja: 1 -- ENTRAR");

			System.out.println("Digite a opçao que deseja: 2 -- CADASTRO");

			int opçao = leitor.nextInt();

			if (opçao == 1) {
				System.out.println();
				System.out.println("Seu código: ");
				int codigoVal = leitor.nextInt();
				System.out.println("Sua senha: ");
				String senhaVal = leitor.next();

				Cliente cliente = banco.getClientes().stream()
						.filter(c -> c.getCodigo() == codigoVal && c.getSenha().equals(senhaVal)).findFirst()
						.orElse(null);

				if (cliente == null) {
					System.out.println("Cliente não encontrado ou senha incorreta!");
					continue;
				}

				System.out.println("Login com sucesso! O que deseja fazer?");
				System.out.println("Sacar (1)");
				System.out.println("depositas (2)");
				System.out.println("Extrato (3)");
				System.out.println("Transferir (4)");
				int opc = leitor.nextInt();

				Conta conta = banco.getContas().stream().filter(t -> t.cliente.getCodigo() == cliente.getCodigo())
						.findFirst().orElse(null);

				if (conta == null) {
					System.out.println("Conta não encontrada!");
					continue;
				}

				switch (opc) {
				case 1: {

					System.out.println("Valor pra sacar");
					double saque = leitor.nextDouble();
					conta.sacar(saque);

				}
				case 2: {
					System.out.println("Valor pra depositar");
					double deposito = leitor.nextDouble();
					conta.sacar(deposito);
				}
				case 3: {
					conta.imprimirExtrato();
				}
				case 4: {
					System.out.println("Cliente destino");
					int destino = leitor.nextInt();
					Cliente clienteDestino = banco.getClientes().stream().filter(c -> c.getCodigo() == destino)
							.findFirst().orElse(null);
					if (clienteDestino == null) {
						System.out.println("Cliente não encontrado");
						break;
					}

					System.out.println("Conta destino");
					int destinoo = leitor.nextInt();

					Conta contaDestino = banco.getContas().stream().filter(c -> c.getNumero() == destinoo).findFirst()
							.orElse(null);
					if(contaDestino==null) {
						System.out.println("Conta nao encontrada");
						break;
					}
					System.out.println("Valor pra transferir");
					double valor = leitor.nextDouble();
					conta.transferir(valor, contaDestino);
					System.out.println("Sucesso! ");
					break;
					
				}
				default:
					System.out.println("opcao errada");
					break;
				}

			} else if (opçao == 2) {
				System.out.println("BEM VINDO AO CADASTRO DO BANCO. " + banco);

				// criando cliente
				System.out.println("Primeiro nome: ");

				String nome = leitor.next();

				System.out.println("Segundo nome: ");

				String sobrenome = leitor.next();

				System.out.println("Data de nascimento: ");

				Date date = null;

				while (date == null) {
					try {
						System.out.println("Digite a data de nascimento (yyyy-mm-dd):");
						String data = leitor.nextLine();
						date = converte(data);
					} catch (IllegalArgumentException e) {
						System.out.println("Data inválida. Por favor, use o formato yyyy-mm-dd.");
					}
				}

				System.out.println("Telefone: ");

				String telefone = leitor.nextLine();

				System.out.println("Senha: ");

				String senha = leitor.nextLine();

				Cliente cliente = new Cliente(nome, sobrenome, date, telefone, senha);
				banco.getClientes().add(cliente);
				System.out.println("CONTRA DO CLIENTE " + cliente.getNome() + " criada");
				System.out.println("Seu codigo " + cliente.getCodigo());

				Conta conta;
				System.out.println("Deseja criar uma conta corrente (1) ou poupança (2)?");
				int op = leitor.nextInt();
				if (op == 1) {
					conta = new ContaCorrente(cliente);
				} else {
					conta = new ContaPoupanca(cliente);
				}
				banco.getContas().add(conta);
				System.out.println("Contra criada com sucesso! ");

			} else {
				System.out.println("Opcao errada! ");
			}
		}

	}

	private static Date converte(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			java.util.Date parsed = format.parse(dateStr);
			return new Date(parsed.getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Data inválida");
		}
	}
}
