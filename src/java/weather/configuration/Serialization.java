package weather.configuration;

import java.io.*;

public class Serialization {
    private final String pathToFile =
            PathConfig.projectPath + "src" + File.separator + "resources" + File.separator + "serializableObjects" + File.separator;

    public void objectSerializable(Serializable obj) {
        File file = this.isCreatedFile(obj.getClass().getSimpleName());
        try(ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public Serializable getObjectByDeserializable(Serializable obj){
        File file = this.isCreatedFile(obj.getClass().getSimpleName());
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            obj = (Serializable) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private File isCreatedFile(String filename){
        File file = new File(pathToFile + filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
        return file;
    }
}
