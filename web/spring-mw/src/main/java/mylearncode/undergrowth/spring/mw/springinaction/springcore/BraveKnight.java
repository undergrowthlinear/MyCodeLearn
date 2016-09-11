package mylearncode.undergrowth.spring.mw.springinaction.springcore;

public class BraveKnight implements Knight {

	private Quest quest;

	public BraveKnight(Quest quest) {
		super();
		this.quest = quest;
	}

	@Override
	public void embarkOnQuest() {
		// TODO Auto-generated method stub
		quest.embark();
	}

}
