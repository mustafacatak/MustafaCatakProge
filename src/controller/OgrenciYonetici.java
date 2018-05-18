/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Ogrenci;
import org.hibernate.Session;

public class OgrenciYonetici {

    private JTable ogrenciTablo;
    private final static String SORGU_KALIP = "from Ogrenci o";
    private Session session;
    private Vector<String> sutunlar = new Vector<>();
    private Vector<Object> satir;
    private DefaultTableModel model;

    // iki tane constructor var biri parametre alıyor diğeri almıyor. 
    // jtablea veri çekilecekse parametre alan kullanılabilir.
    // onun dışımdakilerde ise parametresiz olanı kullanabiliriz.
    public OgrenciYonetici(JTable ogrenciTablo) {
        this.ogrenciTablo = ogrenciTablo;
        sutunlar.add("Ögrenci ID");
        sutunlar.add("Öğrenci No");
        sutunlar.add("Ad Soyad");
        sutunlar.add("Şehir");
        sutunlar.add("Tel No");
        model = (DefaultTableModel) ogrenciTablo.getModel();
        model.setColumnIdentifiers(sutunlar);
    }

    public OgrenciYonetici() {

    }

    //Hibernate mysql bağlantısını açıyor.
    public void ac() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    //Hibernate mysql bağlantısını kapatıyor.
    public void kapat() {
        session.close();
    }

    // Ogrenci getir overload fonksiyondur. 3 versiyonu var.
    // Parametresiz olan bütün öğrenci listesini alıyor.
    // id parametle alan öğrenci numarasına göre öğrencileri alıyor.
    // diğeride arama yaparak geiriyor. ad ve öğrenci numarasına göre.
    public void ogrenciGetir(String aranan, String filtre) {

        String sorguMetin = "";
        if (filtre.equalsIgnoreCase("adsoyad")) {
            sorguMetin = SORGU_KALIP + " where o.adsoyad like '%" + aranan + "%'";
        } else if (filtre.equalsIgnoreCase("ogrencino")) {
            sorguMetin = SORGU_KALIP + " where o.ogrencino like '%" + aranan + "%'";
        }
        session.beginTransaction();
        List ogrencilerList = session.createQuery(sorguMetin).list();
        session.getTransaction().commit();
        ogrenciGoster(ogrencilerList);
    }

    public void ogrenciGetir() {
        String sorguMetin = SORGU_KALIP;
        session.clear();
        TabloyuSil();
        session.beginTransaction();
        List ogrencilerList = session.createQuery(sorguMetin).list();
        session.getTransaction().commit();
        ogrenciGoster(ogrencilerList);
    }

    public Ogrenci ogrenciGetir(String Id) {
        System.out.println(Id);
        session.beginTransaction();
        Ogrenci n = session.createQuery(
                "select e "
                + "from Ogrenci e "
                + "where "
                + "    e.ogrenciid = '" + Id + "' ",
                Ogrenci.class)
                .getSingleResult();

        session.getTransaction().commit();
        return n;
    }

    private void ogrenciGoster(List<Ogrenci> ogrencilerList) {
        model.getDataVector().removeAllElements();
        for (Ogrenci gelenOgrenci : ogrencilerList) {
            satir = new Vector();
            satir.add(gelenOgrenci.getOgrenciid());
            satir.add(gelenOgrenci.getOgrencino());
            satir.add(gelenOgrenci.getAdsoyad());
            satir.add(gelenOgrenci.getSehir());
            satir.add(gelenOgrenci.getTelno());
            model.addRow(satir);
        }
    }

    // bu method jTable içinde ki bütün elamanları silip sıfırlıyor
    public void TabloyuSil() {
        model.setRowCount(0);
    }

    // bu method öğrenci id sine göre öğrenciyi siliyor.
    public void OgrenciSil(int Id) {
        String ogrenciid = model.getValueAt(Id, 0).toString();
        session.beginTransaction();
        Ogrenci n = session.createQuery(
                "select e "
                + "from Ogrenci e "
                + "where "
                + "    e.ogrenciid = '" + ogrenciid + "' ",
                Ogrenci.class)
                .getSingleResult();
        session.delete(n); //silme
        session.getTransaction().commit();
        model.removeRow(Id);
    }

    // bu method aldığı parametrelere göre yeni bir öğrenci veritabanına ekliyor.
    public void OgrenciEkle(String Ogrencino, String Adsoyad, String Sehir, String Telefon) {
        Ogrenci ogr = new Ogrenci();
        ogr.setAdsoyad(Adsoyad);
        ogr.setOgrencino(Ogrencino);
        ogr.setSehir(Sehir);
        ogr.setTelno(Telefon);

        session.beginTransaction();
        session.save(ogr);//ekleme
        session.getTransaction().commit();
    }

    // bu method doğrudan Ogrenci model parametresini alıp güncelleme yapıyor.
    public void OgrenciGuncelle(Ogrenci ogr) {

        session.beginTransaction();
        session.merge(ogr);// güncelleme
        session.getTransaction().commit();

    }
}
