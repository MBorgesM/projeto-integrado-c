package entrega1;

public class Pilha<X> {
	private Object[][] coordenadas;
	private int maxTam;
	private int ultimo;
	private static final String ERRO1 = "Pilha Vazia";
	
	/** Construtor padr�o.
	 * 
	 * @param nLinhas conta a quantidade de linhas
	 * @param nColunas conta a quantidade de colunas
	 */
	public Pilha(int nLinhas, int nColunas) {
		this.maxTam = nLinhas * nColunas;
		this.coordenadas = new Object [maxTam][2];
		this.ultimo = -1;
	}

	/** Respons�vel pela incrementa��o de elementos no topo da pilha.
	 * 
	 * @param x parametro para que guarda a coordenada no eixo x.
	 * @param y parameto que guarda a coordenada no eixo y.
	 * @throws Exception Lan�a exce��o.
	 */
	public void push(X x, X y) throws Exception {
		if (x == null) {
			throw new Exception("Valor Ausente");
		}
		if (y == null) {
			throw new Exception("Valor Ausente");
		}
		if (this.ultimo + 1 == this.maxTam) {
			throw new Exception("Pilha Cheia");
		}

		this.ultimo++;
		this.coordenadas[this.ultimo][0] = x;
		this.coordenadas[this.ultimo][1] = y;
	}

	/** Retorna o que est� guardado no topo da pilha na posi��o X.
	 * 
	 * @return O valor do �ltimo elemento da pilha na posi��o X.
	 * @throws Exception lan�a exce��o.
	 */
	@SuppressWarnings("unchecked")
	public X topX() throws Exception {
		if (isVazia()) {
			throw new Exception(ERRO1);
		}

		return (X)this.coordenadas[this.ultimo][0];
	}

	/** Retorna o que est� guardado no topo da pilha na posi��o Y.
	 * 
	 * @return O valor do �ltimo elemento da pilha na posi��o Y.
	 * @throws Exception lan�a exce��o.
	 */
	@SuppressWarnings("unchecked")
	public X topY() throws Exception {
		if (isVazia()) {
			throw new Exception(ERRO1);
		}

		return (X)this.coordenadas[this.ultimo][1];
	}

	/** Tem a fun��o de remover o elemento que est� no topo da pilha.
	 * 
	 * @throws Exception lan�a exce��o.
	 */
	public void pop() throws Exception {
		if (isVazia()) {
			throw new Exception(ERRO1);
		}

		this.ultimo--;
	}

	/** Respons�vel por limpar o que tem guardado em pilha.
	 * 
	 * @throws Exception lan�a exce��o.
	 */
	public void clear() throws Exception {
		if (isVazia()) {
			throw new Exception(ERRO1);
		}

		while (this.ultimo != -1) {
			this.coordenadas[this.ultimo] = null;
			this.ultimo--;
		}
	}

	/** Verifica se a pilha est� vazia.
	 * 
	 * @return true se a pilha est� vazia, se n�o, false
	 */
	public boolean isVazia() {
		return this.ultimo == -1;
	}
	
	/**
	 * Respons�vel por imprimir o que tem guardado na Pilha na tela.
	 */
	@Override
	public String toString() {
		String ret = "";
		
		if (this.isVazia()) {
			ret = ERRO1;
			return ret;
		}
		while(!this.isVazia()) {
			ret += this.coordenadas[this.ultimo][0] + ", " + this.coordenadas[this.ultimo][1] + "\n";
			try {
				this.pop();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		return ret;
	}
	
	/*
	 * Verifica se duas pilhas s�o iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		return false;
	}
	
	public Pilha(Pilha<X> modelo) throws Exception {
		if (modelo == null) {
			throw new Exception ("Pilha Ausente");
		}
		
		this.maxTam = modelo.maxTam;
		this.ultimo = modelo.ultimo;
		
		for (int i = 0; i < this.ultimo; i++) {
			this.coordenadas[i][0] = modelo.coordenadas[i][0];
			this.coordenadas[i][1] = modelo.coordenadas[i][1];
		}
	}
}