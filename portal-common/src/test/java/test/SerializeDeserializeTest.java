package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import my.portal.common.DtoMap;
import my.portal.common.dto.ImageLinkDto;

public class SerializeDeserializeTest {

	public static void main(String[] args) {

		DtoMap<String, ImageLinkDto> map = new DtoMap<>();
		map.put("test-0", new ImageLinkDto());
		map.put("test-1", new ImageLinkDto("targetUrl","imageUrl", "text", null));
		map.put("test-2", new ImageLinkDto("targetUrl","imageUrl", "text", new ArrayList<>()));
		
		List<ImageLinkDto> subList = new ArrayList<>();
		subList.add(new ImageLinkDto("targetUrl","imageUrl", "text", null));
		map.put("test-3", new ImageLinkDto("targetUrl","imageUrl", "text", subList));
		
		//write object
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
			oos.writeObject(map);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//read object
		ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
		try(ObjectInputStream ois = new ObjectInputStream(bis)){
			DtoMap desMap = (DtoMap)ois.readObject();
			desMap.forEach((k,v) -> System.out.println("key:"+k+ " value:"+v));
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
