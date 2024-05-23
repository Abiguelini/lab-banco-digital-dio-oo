import java.sql.Date;

public class Cliente {
	
	private static int CODIGO_SEQUENCIAL = 1;
	private int codigo;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String telefone;
	private String senha;
	
	
	//atributos
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getSenha() {
		return senha;
	}
	
	
	//construtor
	public Cliente( String nome, String sobrenome, Date dataNascimento, String telefone, String senha) {
		super();
		this.codigo = CODIGO_SEQUENCIAL++;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.senha = senha;
	}
	
	
	

}
