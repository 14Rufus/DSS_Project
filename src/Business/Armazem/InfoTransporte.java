package Business.Armazem;

public class InfoTransporte {
    private int idinfoTransporte;
    private String qrCode;
    private int prateleira;

    public InfoTransporte(int id, String qr, int prat) {
        idinfoTransporte = id;
        qrCode = qr;
        prateleira = prat;
    }

    public int getIdinfoTransporte() {
        return idinfoTransporte;
    }

    public void setIdinfoTransporte(int idinfoTransporte) {
        this.idinfoTransporte = idinfoTransporte;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }
}
