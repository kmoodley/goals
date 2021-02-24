package com.goals.track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TrackComponentsTests
{
    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void contextLoads()
    {
        LOG.info("inside contextLoads");
    }

    @Test
    void testGoalCreation()
    {
        /*TaskComponent taskGroup_Book1 = new TaskGroup("Book 1", 0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C1 = new Task("A Long-expected Party",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C2 = new Task("The Shadow of the Past",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C3 = new Task("Three is Company",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C4 = new Task("A Short Cut to Mushrooms",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C5 = new Task("A Conspiracy Unmasked",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C6 = new Task("The Old Forest",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C7 = new Task("In the House of Tom Bombadil",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C8 = new Task("Fog on the Barrow-downs",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C9 = new Task("At the Sign of the Prancing Pony",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C10 = new Task("Strider",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C11 = new Task("A Knife in the Dark",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book1_C12 = new Task("Flight to the Ford",0.00, LocalDate.of(2021,3,20));

        Arrays.asList(task_Book1_C1, task_Book1_C2,task_Book1_C3,task_Book1_C4,task_Book1_C5,task_Book1_C6,
                task_Book1_C7,task_Book1_C8,task_Book1_C9,task_Book1_C10,task_Book1_C11,task_Book1_C12)
                .stream()
                .forEach(t -> taskGroup_Book1.add(t));

        taskGroup_Book1.print();

        TaskComponent taskGroup_Book2 = new TaskGroup("Book 2", 0.00, LocalDate.of(2021,4,20));
        TaskComponent task_Book2_C1 = new Task("Many Meetings",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C2 = new Task("The Council of Elrond",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C3 = new Task("The Ring goes South",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C4 = new Task("A Journey in the Dark",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C5 = new Task("The Bridge of Khazad-dûm",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C6 = new Task("Lothlórien",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C7 = new Task("The Mirror of Galadriel",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C8 = new Task("Farewell to Lórien",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C9 = new Task("The Great River",0.00, LocalDate.of(2021,3,20));
        TaskComponent task_Book2_C10 = new Task("The Breaking of the Fellowship",0.00, LocalDate.of(2021,3,20));

        Arrays.asList(task_Book2_C1,task_Book2_C2,task_Book2_C3,task_Book2_C4,task_Book2_C5,task_Book2_C6,task_Book2_C7,
                task_Book2_C8,task_Book2_C9,task_Book2_C10)
                .stream()
                .forEach(t -> taskGroup_Book2.add(t));

        taskGroup_Book2.print();*/
    }



}
