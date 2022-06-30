package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 判断integer与int的值的关系
         */
//        int i=19;
//        Integer j=0;
//        if(j==null){
//            System.out.println(11);
//        }
//        System.out.println(i==j);
////        System.out.println(j.equals(i));


        /**
         * 利用三元表达式处理专辑数与页数
         */
//        int size=12;
//        int albumsize=size%4!=0?size/4+1:size/4;
//        System.out.println(albumsize);


        /**
         * MD5字节数组实验
         */
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] b_utf8 = md.digest("123".getBytes("UTF-8"));
//
//            System.out.println(b_utf8);
//            for (int i = 0; i < b_utf8.length; i++) {
//                int n=b_utf8[i];
//                System.out.println(n);
//                System.out.println(n/16);
//                System.out.println(n%16);
//            }
//        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


        /**
         * stringBuilder的append连接
         */
//        StringBuilder builder = new StringBuilder();
//        String[] a={"李树新","司马懿","诸葛亮"};
//        for (int i = 0; i < a.length; i++) {
//            System.out.println("第一次："+builder);
//            builder.append(a[i]);
//            if(i!=a.length-1){
//                builder.append("$");
//            }
//            System.out.println("第二次："+builder);
//        }
//        System.out.println(a.toString());


        /**
         * 开启线程的方式：实现implement Runnable的接口
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();


        /**
         * 开启线程的方式：实现Callable接口、重写call方法、创建ExecutorSercice线程池.
         *
         * * 5,将自定义的对象放入线程池中。
         *
         * * 6，获取线程返回结果。
         *
         * * 7，关闭线程，不在接受新的线程，未执行的完的线程不会被关闭
         */
//        ExecutorService es = Executors.newCachedThreadPool();
//        Future<Integer> f1 = es.submit(new call(5));
//
//        Future<Integer> f2 = es.submit(new call(3));
//
//        if (f1.isDone()) {
//
//            System.out.println(f1.get());
//
//        }else {
//
//            System.out.println("f1线程未执行完毕！");
//
//        }
//
//        if (f2.isDone()) {
//
//            System.out.println(f2.get());
//
//        }else {
//
//            System.out.println("f2线程未执行完毕！");
//
//        }
//
//
//        es.shutdown();

//        new Thread(String.valueOf(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return null;
//            }
//        })).start();
//
//    }
//    static class call implements Callable<Integer>{
//
//        private int i;
//
//        public call(int i){
//            this.i=i;
//        }
//        @Override
//        public Integer call() throws Exception {
//            return i;
//        }
//    }

        /**
         * Map:HashMap
         */
//       Map<String, String> maptest=new HashMap();
//        maptest.put("1","Hello1");
//        maptest.put("2","Hello2");
//        maptest.put("3","Hello3");
//        maptest.put("4","Hello4");
//
//        System.out.println(maptest.entrySet());
//        System.out.println(maptest.keySet());

        String str="19997";
        String[] strings=str.split("9");

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);

        }
    }
}
