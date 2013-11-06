package gui;

import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import controllers.ListingController;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Show;

public class Search extends javax.swing.JFrame {
    
    public Search() {
        initComponents();
    }
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Cineplex = new javax.swing.JLabel();
        Movie = new javax.swing.JLabel();
        CineplexList = new javax.swing.JComboBox();
        MovieList = new javax.swing.JComboBox();
        List = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTime = new javax.swing.JTextArea();

        Cineplex.setText("Cineplex:");

        Movie.setText("Movie:");
        
        List<Cineplex> cineplexList = ListingController.getCineplexList();
        List<String> cineplexNameList = new ArrayList<String>();
        String cineplexItem = "";
        for (Cineplex cineplex : cineplexList){
        	cineplexItem = cineplex.getId() + " " + cineplex.getName();
        	cineplexNameList.add(cineplexItem); 
        	}
        String[] cineplexNameArray = cineplexNameList.toArray(new String[0]);
        CineplexList.setModel(new javax.swing.DefaultComboBoxModel(cineplexNameArray));
        CineplexList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CineplexListActionPerformed(evt);
            }
        });
        
        String movieItem = "";
        Cineplex defaultCineplex = cineplexList.get(0);
        List<Movie> movieList = ListingController.getMovieByCineplex(defaultCineplex);
        List<String> movieNameList = new ArrayList<String>();
        for (Movie movie: movieList){
        	movieItem = movie.getId() + " " + movie.getName();
        	movieNameList.add(movieItem); 
        	}
        String[] movieNameArray = movieNameList.toArray(new String[0]);
        MovieList.setModel(new javax.swing.DefaultComboBoxModel(movieNameArray));
        MovieList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MovieListActionPerformed(evt);
            }
        });

        List.setText("List");
        List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        
        ShowTime.setEditable(false);
        ShowTime.setOpaque(false);
        ShowTime.setColumns(50);
        ShowTime.setRows(5);
        ShowTime.setText("Movies listed...");
        jScrollPane1.setViewportView(ShowTime);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Searching & Listing");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movie Searching", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 204)));
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Cineplex)
                    .addComponent(Movie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CineplexList, 0, 220, Short.MAX_VALUE)
                    .addComponent(MovieList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(List))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CineplexList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cineplex))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MovieList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Movie, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(List)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Showtime", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 255)));
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Close)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Close)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                        

    private void CineplexListActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String selectedCineplex = (String)CineplexList.getSelectedItem();
        String[] temp = selectedCineplex.split(" ");
        int cineplexId = Integer.parseInt(temp[0]);
        List<Movie> movieList = ListingController.getMovieByCineplex(cineplexId);
        List<String> movieNameList = new ArrayList<String>();
        String movieItem = "";
        for (Movie movie: movieList){
        	movieItem = movie.getId() + " " + movie.getName();
        	movieNameList.add(movieItem);
        	} 
        String[] movieNameArray = movieNameList.toArray(new String[0]);
        MovieList.setModel(new javax.swing.DefaultComboBoxModel(movieNameArray));
    }                                          

    private void MovieListActionPerformed(java.awt.event.ActionEvent evt) {
    }                                          

    private void ListActionPerformed(java.awt.event.ActionEvent evt) {
    	String selectedCineplex = (String)CineplexList.getSelectedItem();
    	String selectedMovie = (String)MovieList.getSelectedItem();
        String[] temp = selectedCineplex.split(" ");
        int cineplexId = Integer.parseInt(temp[0]);
        temp = selectedMovie.split(" ");
        int movieId = Integer.parseInt(temp[0]);
    	List<Show> showList = ListingController.getShowByCineplextAndMovie(cineplexId, movieId);
    	
    	String display = "Cinema    Showtime            Type    Rating\n";
    	for(Show show : showList){
    		String cinema = show.getCinema().getCinemaCode();
    		String showtime = show.getShowTime().toString();
    		String type = show.getMovie().getType();
    		String rating = show.getMovie().getRating();
    		String item = String.format("%-10s%-20s%-8s%s\n",cinema,showtime,type,rating);
    		display.concat(item);
    	}
    	
        ShowTime.setText(display);
    }                                        

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }                                        

    private javax.swing.JButton List;
    private javax.swing.JButton Close;
    private javax.swing.JComboBox CineplexList;
    private javax.swing.JComboBox MovieList;
    private javax.swing.JLabel Cineplex;
    private javax.swing.JLabel Movie;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ShowTime;              
}
