package ai.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import test.Coughing;
import test.FaceTo;
import test.HasSound;
import test.Hovering;
import test.Idle;
import test.LookAround;
import test.MoveTo;
import test.NoHasCoughFeeling;
import test.NoHasQiangDao;
import test.Smoking;
import test.ToCD;
import test.ToSkill;
import test.hasMonster;
import test.hasSkill;

import com.thoughtworks.xstream.XStream;

public class AiManager {

	private Map<String, Node> ai = new HashMap<String, Node>();
	private static XStream xstream = new XStream();
	static {
		//正式时可以用加载时自动扫描相应的包并自动通过注解注册(此处只是例子)
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.autodetectAnnotations(true);
		xstream.alias("NodeParallel", NodeParallel.class);
		xstream.alias("NodeSelector", NodeSelector.class);
		xstream.alias("NodeSequence", NodeSequence.class);
		xstream.alias("NodeTerminal", NodeTerminal.class);
		xstream.alias("Coughing", Coughing.class);
		xstream.alias("FaceTo", FaceTo.class);
		xstream.alias("hasMonster", hasMonster.class);
		xstream.alias("hasSkill", hasSkill.class);
		xstream.alias("HasSound", HasSound.class);
		xstream.alias("Hovering", Hovering.class);
		xstream.alias("Idle", Idle.class);
		xstream.alias("LookAround", LookAround.class);
		xstream.alias("MoveTo", MoveTo.class);
		xstream.alias("NoHasCoughFeeling", NoHasCoughFeeling.class);
		xstream.alias("NoHasQiangDao", NoHasQiangDao.class);
		xstream.alias("Smoking", Smoking.class);
		xstream.alias("ToCD", ToCD.class);
		xstream.alias("ToSkill", ToSkill.class);
		
	}

	public Node getAi(String id) {
		return ai.get(id);
	}

	public void init(String path) {
		File file = new File(path);
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			String name = f.getName();
			if (name.endsWith(".xml")) {
				Node root = (Node) xstream.fromXML(f);
				String n = name.substring(0, name.lastIndexOf("."));
				ai.put(n, root);
			}
		}

	}
}
