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
            writer.writeCharacters("\n");
            writer.writeStartElement("weapons");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns", "http://www.vsu.by/weapons");
            writer.writeAttribute("xsi:schemaLocation", "http://www.vsu.by/weapons weapons.xsd");
            writer.writeCharacters("\n");
            for (Weapon weapon : weapons) {
                writer.writeStartElement("weapon");
                writer.writeAttribute("id", weapon.getId());
                writer.writeCharacters("\n");

                writeSimpleElement(writer, "Type", weapon.getWeaponType().toString());
                writeSimpleElement(writer, "Handy", weapon.getHandleType().toString());
                writeSimpleElement(writer, "Origin", weapon.getOrigin());

                writer.writeStartElement("Visual");
                for (VisualTypeWeapon visualTypeWeapon : weapon.getVisual()) {
                    writeSimpleElement(writer, visualTypeWeapon.getName(), visualTypeWeapon.getValue());
                }
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writeSimpleElement(writer, "Value", Boolean.toString(weapon.getValue()));

                writer.writeEndElement();
                writer.writeCharacters("\n");
            }
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void writeSimpleElement(XMLStreamWriter writer, String nameElement, String nameCharacteristics) throws XMLStreamException {
        writer.writeStartElement(nameElement);
        writer.writeCharacters(nameCharacteristics);
        writer.writeEndElement();
        writer.writeCharacters("\n");
    }

}
