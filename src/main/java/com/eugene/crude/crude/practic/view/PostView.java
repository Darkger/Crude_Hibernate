package com.eugene.crude.crude.practic.view;
import com.eugene.crude.crude.practic.controller.PostController;
import com.eugene.crude.crude.practic.model.Post;
import com.eugene.crude.crude.practic.model.builder.builderImpl.PostBuilderImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PostView implements View {

    PostController postController ;

    public PostView(){
        this.postController =  new PostController();
    }

    public void viewDeleteById(String str) {

        postController.deleteById(str);
        System.out.println("Пост с id=" + str + " удален из файла");
    }

    public void viewSave(Post post)  {

        post = postController.save(post);
        if (post != null)
            System.out.println("Пост" + "'" + post.getName() + "'" + " сохранен с id=" + post.getId());
        else System.out.println("Ошбика:Пост не может быть сохранен!");
    }

    public void viewGetAll()  {

        List<Post> regionList = postController.getAll();
        if (regionList==null)
            System.out.println("Файл пуст");
        else {
            System.out.println("Список постов:");
            for (Post region : regionList) {
                System.out.println(region.getId() + "," + region.getName());
            }
        }
    }

    public void viewUpdate(Post post)  {

        Post post1 = postController.update(post);
        if (post1 != null)
            System.out.println("Идентификатор id=" + post.getId() + " теперь присвоен посту '" + post.getName() + "'");
        else System.out.println("Ошибка:Пост не может быть изменен");
    }

    public void viewGetElementById(String str)  {

        Post post = postController.getElementById(str);
        if (post != null)
            System.out.println("Идентификатор id=" + post.getId() + "принадлежит посту '" + post.getName());
        else System.out.println("Пост не найден!");
    }

    public String routing()  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("РАБОТА С ФАЙЛОМ  'post.txt':\n\n");
        System.out.println("Доступный список действий:\n ");
        System.out.println("1. Введите команду -'LIST'   чтобы получить список всех постов");
        System.out.println("2. Введите команду -'BYID'   чтобы получить пост по id ");
        System.out.println("3. Введите команду -'SAVE'   чтобы сохранить пост");
        System.out.println("4. Введите команду -'UPDATE' чтобы изменить пост");
        System.out.println("5. Введите команду -'DELETE' чтобы удалить  пост");
        System.out.println("6. Введите команду -'USER' для перехода к файлу 'user.json':");
        System.out.println("7. Введите команду -'REG' для перехода к файлу 'region.json':");

        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (str) {
            case "LIST": {
                viewGetAll();
                break;
            }
            case "BYID": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Double.parseDouble(id);

                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели некорректный ID,повторите попытку");
                    break;
                }
                viewGetElementById(id);
                break;

            }
            case "SAVE": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Double.parseDouble(id);

                } catch (NumberFormatException e) {
                    System.out.println("Вы ввели некорректный ID,повторите попытку");
                    break;
                }
                System.out.println("Введите Пост: ");
                String postName = null;
                try {
                    postName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Post post = new PostBuilderImpl(Integer.parseInt(id),postName).build();
                viewSave(post);
                break;
            }
            case "UPDATE": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Введите пост: ");
                String postName= null;
                try {
                    postName = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Post post = new PostBuilderImpl(Integer.parseInt(id),postName).build();
                viewUpdate(post);
                break;
            }
            case "DELETE": {
                System.out.println("Введите id: ");
                String id = null;
                try {
                    id = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewDeleteById(id);
                break;
            }
            case "USER":{
                return "USER";
            }
            case "REG":{
                return "REG";
            }
        }
        System.out.println("Введите 'POST' для перехода к файлу 'post.json':");
        System.out.println("Продолжить работу? Y/N :");
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (str.equals("N"))
            return "N";
        else return "Y";
    }
}

