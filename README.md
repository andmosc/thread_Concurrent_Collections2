## Задача 1. Программа-анализатор

## Описание
Ваш коллега из первой задачи до сих пор ломает голову над математической статистикой. Благодаря уже известному вам генератору, он создает **из символов "abc" 10_000 текстов длиной 100_000 каждый**.

<details>
  <summary>Генератор текстов</summary>

  ```java
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
  ```
</details>

При этом теперь его интересует как бы выглядел текст, в котором содержится максимальное количество:
* символов **'a'**;
* символов **'b'**;
* символов **'c'**;

Попробуем решить эту задачу многопоточно: чтобы за анализ строк на предмет максимального количества каждого из трёх символов отвечал отдельный поток. *То есть за поиск строки с самым большим количеством символов **'a'** отвечал бы один поток, за поиск с самым большим количеством **'b'** - второй и за **'c'** - третий.*

Однако, сгенерировать все тексты, сохранить их в массив и затем пройтись по ним было бы неправильно, т.к. суммарно в текстах было бы около 1 млрд. символов, что привело бы к избыточному расходу памяти. Мы можем пойти другим путём и распараллелить этап создания строк и этапы их анализа.

Для этого строки будут генерироваться в отдельном потоке и заполнять блокирующие очереди, максимальный размер которых **ограничим 100 строками**.
Очереди нужно будет сделать по одной для каждого из трёх анализирующих потоков, т.к. строка должна быть обработана каждым таким потоком.