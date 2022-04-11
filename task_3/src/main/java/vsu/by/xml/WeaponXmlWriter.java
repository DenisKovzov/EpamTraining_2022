package vsu.by.xml;

import vsu.by.models.Weapon;
import vsu.by.models.visualWeapon.VisualTypeWeapon;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class WeaponXmlWriter {
    public void write(List<Weapon> weapons, String fileName) throws FileNotFoundException, XMLStreamException {
        XMLStreamWriter writer = null;

        try {
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            writer = factory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("weapons");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns", "http://www.vsu.by/weapons");
            writer.writeAttribute("xsi:schemaLocation", "http://www.vsu.by/weapons weapons.xsd");

            for (Weapon weapon : weapons) {
                writer.writeStartElement("weapon");
                writer.writeAttribute("id", weapon.getId());

                writer.writeStartElement("Type");
                writer.writeCharacters(weapon.getWeaponType().toString());
                writer.writeEndElement();

                writer.writeStartElement("Handy");
                writer.writeCharacters(weapon.getHandleType().toString());
                writer.writeEndElement();

                writer.writeStartElement("Origin");
                writer.writeCharacters(weapon.getOrigin());
                writer.writeEndElement();

                writer.writeStartElement("Visual");
                for (VisualTypeWeapon visualTypeWeapon : weapon.getVisual()) {
                    writer.writeStartElement(visualTypeWeapon.getName());
                    writer.writeCharacters(visualTypeWeapon.getValue());
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement("Value");
                writer.writeCharacters(Boolean.toString(weapon.getValue()));
                writer.writeEndElement();

                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
