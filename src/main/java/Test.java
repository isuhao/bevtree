import test.Monster;
import ai.core.AiManager;
import ai.core.Node;
import ai.core.NodeInputParam;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		//------------------------------------------------
//		Node _root = new NodeSelector("root").addChild(
//		        new NodeSequence("move").setPrecondition(new HasSound()).addChild(new FaceTo("FaceTo"))
//		                .addChild(new Idle("Idle")).addChild(new MoveTo("MoveTo"))
//		                .addChild(new LookAround("LookAround")))
//		        .addChild( new NodeSequence("fight").setPrecondition(new hasMonster()).addChild(new ToCD("cd", 5000))// 5秒
//		                .addChild(new ToSkill("skill", 1000)))
//		// 以上移动节点 如果有声音，面朝声音，闲置2次防止过快反应，移动到声音处，判断是否有怪物和技能是否在CD，通过判断执行技能
//		        .addChild(new NodeParallel("patrol").addChild(new Hovering("Idle")).addChild(
//		        				// 此处徘徊节点 如果没有怪物吸烟，如果有咳嗽的感觉则咳嗽
//		                        new NodeSelector("smoking").addChild(
//		                                new Smoking("Smoking").setPrecondition(new NoHasQiangDao())).addChild(
//		                                new Coughing("Coughing").setPrecondition(new NoHasCoughFeeling()))));

//		 XStream xstream = new XStream();
//		//正式时可以用加载时自动扫描相应的包并自动通过注解注册(此处只是例子)
//		xstream.setMode(XStream.ID_REFERENCES);
//		xstream.autodetectAnnotations(true);
//		System.out.println(xstream.toXML(_root));
		
		//--------------------------ToCD类用于冷却---------------------------------------
		
		//每个ai对应一个怪物，ai不能公用，因为有上下文。怪死后再生成的可以使用原有配置的ai.
		//先加载ai然后，加载怪物时通过id获得相应的怪物ai.
		//ai的xml由ai编辑器生成。
		// -------------------通过文件加载----------------------------
		String path = Test.class.getResource("/ai").getPath();
		AiManager ai=new AiManager();
		ai.init(path);
		Node _root = ai.getAi("1");
		// ------------------------------------------------
		System.out.println(_root);

		Monster monster = new Monster();

		NodeInputParam input = new NodeInputParam(monster);// 这里是传入ai的数据，此处是假的，根据实际情况放入
		for (int i = 0; i < 50; i++) {
			Boolean evaluate = _root.evaluate(input);
			if (evaluate) {
				_root.tick(input);
			}
			System.out.println("----------------------------");
			Thread.sleep(1000);
		}
	}
}