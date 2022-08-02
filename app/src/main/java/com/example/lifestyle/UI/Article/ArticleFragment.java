package com.example.lifestyle.UI.Article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.lifestile.R;
import com.example.lifestyle.Models.ArticleModel;
import com.example.lifestyle.UI.MainScreen;
import com.example.lifestyle.UI.Workout.Adapters.ArticleModelAdapter;

import java.util.ArrayList;

public class ArticleFragment extends Fragment {

    ListView mlistViewArticle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_article, container, false);
        mlistViewArticle = v.findViewById(R.id.article_list);

        ArrayList<ArticleModel> arrayList = new ArrayList<>();

        arrayList.add(new ArticleModel(1, R.drawable.weights, "Утренний распорядок для максимальной продуктивности",
                "Изучая книги об известных исторических деятелях, Пирсон обнаружил, что вопрос продуктивности интересует людей уже несколько сотен лет. Например, американский политик Бенджамин Франклин выстроил для себя утренний распорядок, чтобы подготовиться к началу рабочего дня. «Я просыпаюсь рано каждое утро, не переодеваясь сразу сажусь за стол и провожу полчаса или час — в зависимости от времени года — за чтением или писательством», — описывал Франклин своё утро.\n" +
                        "\n" +
                        "Российский композитор Петр Ильич Чайковский начинал своё утро с чашки чая, сигареты и чтения Библии и философских книг. Виктор Гюго просыпался от ежедневного утреннего выстрела пушки в ближайшей крепости, выпивал чашку кофе, читал письма от своей возлюбленной Жюльетты Друэ и съедал два сырых яйца перед тем, как начать работу.\n" +
                        "\n" +
                        "Философ Иммануил Кант начинал свой день со слабозаваренной чашки чая и медитации — за час до того, как приступить к работе. Бетховен начинал день с чашки кофе. Он верил, что 60 зерен кофе — идеальная порция для одной чашки и лично высчитывал их количество.\n" +
                        "\n" +
                        "«Профессор молекулярной биологии Университета Южной Калифорнии Стив Кей в своём исследовании доказал, что большинство взрослых людей, которые начинают утро с подготовки к работе, а не сразу приступают к выполнению дел, показывают лучшие результаты», — пишет предприниматель.",
                "Физическая активность"));

        arrayList.add(new ArticleModel(2, R.drawable.food, "Плюсы и минусы диетических добавок",
                "Пищевые добавки — это природные или синтетические вещества, которые намеренно вносят в пищевые продукты для выполнения определенных технологических функций. Такие вещества называют также прямыми пищевыми добавками. Сейчас очень популярно утверждение, что абсолютно все пищевые добавки приносят только вред. На самом деле, это совсем не так. Они имеют свои плюсы и минусы, а некоторые из них являются даже полезными для человеческого организма.\n" +
                        "\n" +
                        "К главным недостаткам относится их отрицательное влияние на здоровье, особенно на здоровье детей. Различные синтетические пищевые добавки повреждают органы и вызывают их быстрое изнашивание, потому что химикаты тяжело перерабатываются человеческим организмом. В высоких дозировках часть добавок может быть очень опасными.",
                "Питание"));
        arrayList.add(new ArticleModel(3, R.drawable.food, "DASH-диета - оптимальная диета при гипертонии",
                "DASH-диета представляет собой сбалансированную систему здорового питания, которая способствует снижению высокого давления и нормализации массы тела.\n" +
                        "\n" +
                        "Основа диеты при гипертонии — овощи и фрукты, цельнозерновые продукты, бобовые, постные белковые продукты (не красное мясо и нежирная рыба), нежирные молочные продукты. Она предусматривает отказ от жирной, сладкой, соленой, копченой и консервированной пищи. Отдается предпочтение вегетарианскому меню и выбору натуральных продуктов. Важный принцип DASH-диеты — ограничение потребления соли",
                "Питание"));
        arrayList.add(new ArticleModel(4, R.drawable.weights,
                "Утренний распорядок для максимальной продуктивности",
                "У тех, кто стремится к своей цели часто бывают дни, когда нужно работать по максимуму. У студентов — сессия, у белых воротничков — важные моменты в компании. В итоге вы работаете/учитесь с утра до ночи выкладываясь на всю и... выгораете. Вы устаете еще в первой половине дня и вся ваша учеба/работа стает не продуктивной.\n" +
                        "\n" +
                        "Проблема в плохой памяти или низком интеллекте? Вряд ли.\n" +
                        "\n" +
                        "Грамотно распределять день и нагрузки могут только единицы.\n" +
                        "Что если я скажу, что можно заниматься активной интеллектуальной работой 12 часов в день и быть продуктивным без применения фармакологии?\n" +
                        "Более того, поддерживать такой режим на протяжении длительного времени и не выгорать.\n" +
                        "\n" +
                        "Делим день на 3\n" +
                        "\n" +
                        "Эту систему изобрел академик В.А. Обручев. Он делит день на три дня. «Первый» день начинается рано утром и кончается в полдень.\n" +
                        "Учёный обедает, отдыхает и начинает «второй» день, который продолжается до 6 часов вечера. «Третий» день продолжается от ужина до 12 часов ночи. Таким образом, в одном дне у Обручева было три дня, в одном месяце было три месяца.\n" +
                        "\n" +
                        "В «первый» день учёный делал самую трудную работу: он писал научный труд, во «второй» день – более лёгкую работу: писал статьи, рецензии, деловые письма. В «третий» день он читал и писал фантастические рассказы.\n" +
                        "\n" +
                        "И в каждом дне у него были свои дела, прогулки, еда, отдых. Для каждого дня учёный имел специальный ящик, в котором хранил всё, что нужно для работы. В.А. Обручев считал, что главное – правильная организация работы.", "Физическая активность"));

        ArticleModelAdapter modelAdapter = new ArticleModelAdapter(getActivity(), R.layout.item_article, arrayList);

        mlistViewArticle.setAdapter(modelAdapter);
        mlistViewArticle.setClickable(true);
        mlistViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String header = arrayList.get(i).getHeader();
                String desc = arrayList.get(i).getDescription();
                int image = arrayList.get(i).getImage();

                ((MainScreen)getActivity()).openDetailArticleActivity(header,desc,image);


                /*
                Fragment fragment = null;

                switch (i) {
                    case 0:
                        fragment = new DetailArticleFragment();
                        break;
                }

                if (fragment != null) {

                    FragmentTransaction ft = getFragmentManager().beginTransaction();


                    ft.replace(R.id.fragment_container, fragment);

                    ft.addToBackStack(null);

                    ft.commit();
                }
                */
            }
        });

        return v;
    }
}