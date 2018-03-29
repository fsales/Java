package estudo.util.reflection;

public enum FieldUtilTypeEnum {
	STRING("String") {
		@Override
		public Class<?> getType() {

			return String.class;
		}
	};

	private final String nome;

	private FieldUtilTypeEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	/**
	 * @return
	 */
	public abstract Class<?> getType();

}
