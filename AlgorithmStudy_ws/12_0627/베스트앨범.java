import java.util.*;

class Solution {
    static PriorityQueue<Genre> genrePq = new PriorityQueue<>(Comparator.comparingInt(g -> -g.playcnt));
    static HashMap<String, ArrayList<Song>> songMap = new HashMap<>();
    static HashMap<String, Integer> genreMap = new HashMap<>();
    static int[] album;
    public int[] solution(String[] genres, int[] plays) {
        
        // 장르 담기
        for (int id = 0; id < genres.length; id++)
        {
            String genre = genres[id];
            int playcnt = plays[id];
            
            // genre - song list
            ArrayList<Song> tmpSongList = new ArrayList<>();
            Song song = new Song(id, playcnt);
            
            if (songMap.get(genre) == null) {
                tmpSongList.add(song);
                songMap.put(genre, tmpSongList);
            }
            else {
                tmpSongList = songMap.get(genre);
                tmpSongList.add(song);
                songMap.put(genre, tmpSongList);
            }
            
            // genre - play count
            if (genreMap.get(genre) == null)
                genreMap.put(genre, playcnt);
            else 
                genreMap.put(genre, genreMap.get(genre) + playcnt);
        }
        
        // 장르 순서
        for (String genre : genreMap.keySet()) {
            Genre gn = new Genre(genre, genreMap.get(genre));
            genrePq.add(gn);
        }
        
        ArrayList<Integer> albumlist = new ArrayList<>();
        
        // 가장 인기많은 장르부터
        while (!genrePq.isEmpty())
        {
            int max = 0;
            Genre gn = genrePq.poll();
            ArrayList<Song> songlist = new ArrayList<>(songMap.get(gn.name));
            
            songlist.sort(Comparator.comparingInt(s -> -s.playcnt));
            for (Song song : songlist)
            {
                max++;
                albumlist.add(song.id);
                if (max == 2) break;
            }
        }
        
        int idx = 0;
        album = new int[albumlist.size()];
        for (int id : albumlist) album[idx++] = id;
        
        return album;
    }
    
    private static class Genre
    {
        String name;
        int playcnt;
        
        public Genre(String name, int playcnt)
        {
            this.name = name;
            this.playcnt = playcnt;
        }
    }
    
    private static class Song
    {
        int id;
        int playcnt;
        
        public Song(int id, int playcnt)
        {
            this.id = id;
            this.playcnt = playcnt;
        }
    }
}