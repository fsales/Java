package bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import util.DadosUtil;
import dominio.Arquivo;
import exception.RegraNegocioException;
import fachada.ArquivoFachada;

@SessionScoped
@ManagedBean(name = "arquivo")
public class ArquivoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3842361654626134560L;
	private Arquivo arquivo;
	private Arquivo arquivoSelecionado;
	private List<Arquivo> arquivos;
	private StreamedContent file;
	private ArquivoFachada arquivoFachada;
	private DadosUtil dadosUtil;

	public ArquivoBean() {
		arquivoFachada = new ArquivoFachada();
		dadosUtil = new DadosUtil();
	}

	public void prepararAdicionaArquivo(ActionEvent event) {
		arquivo = new Arquivo();
	}

	public void upload(FileUploadEvent event) throws IOException {
		UploadedFile arq = event.getFile();
		String newFileName = "/tmp/" + arq.getFileName();
		File upFile = new File(newFileName);
		try {
			arquivo.setId(null);
			arquivo.setArquivoContentType(event.getFile().getContentType()
					.replace(" ", "").trim());
			arquivo.setArquivoFileName(event.getFile().getFileName()
					.replace(" ", "").trim());

			// salva o arquivo em um diretorio

			FileUtils.copyInputStreamToFile(arq.getInputstream(),
					upFile.getAbsoluteFile());

			arquivo.setData(dadosUtil.carregarBytes(upFile));

			arquivoFachada.salvar(arquivo);
			arquivos = null;
		} catch (RegraNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			upFile.delete();
		}

		FacesMessage msg = new FacesMessage("Sucesso", newFileName);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public Arquivo getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(Arquivo arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public List<Arquivo> getArquivos() throws RegraNegocioException {
		if (arquivos == null) {
			arquivos = arquivoFachada.recuperarTodos();
		}
		return arquivos;
	}

	public StreamedContent getFile() throws RegraNegocioException {

		Arquivo a = arquivoFachada.recuperarPorId(arquivoSelecionado.getId());

		InputStream stream = dadosUtil.criarFileInputStream(a.getData());

		file = new DefaultStreamedContent(stream, a.getArquivoContentType()
				.trim().replace(" ", ""), a.getArquivoFileName().trim()
				.replace(" ", ""));
		return file;
	}

}
