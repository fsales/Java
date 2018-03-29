package dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5628100408736302342L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Lob
	@Column(name = "arquivo")
	private byte[] data;

	@Column(name = "nome_arquivo")
	private String arquivoFileName;

	@Column(name = "arquivo_content_type")
	private String arquivoContentType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclussao")
	private Date dataInclussao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_alteracao")
	private Date dataAlteracao;

	public Arquivo() {
		if (this.id == null) {
			this.dataInclussao = Calendar.getInstance().getTime();
			this.dataAlteracao = Calendar.getInstance().getTime();
		} else {
			this.dataAlteracao = Calendar.getInstance().getTime();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArquivoFileName() {
		return arquivoFileName;
	}

	public void setArquivoFileName(String arquivoFileName) {
		this.arquivoFileName = arquivoFileName;
	}

	public String getArquivoContentType() {
		return arquivoContentType;
	}

	public void setArquivoContentType(String arquivoContentType) {
		this.arquivoContentType = arquivoContentType;
	}

	public Date getDataInclussao() {
		return dataInclussao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
