package gui;

import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Show;

public class Search extends javax.swing.JFrame {
    
    private javax.swing.JButton list;
    private javax.swing.JButton close;
    private javax.swing.JComboBox cineplexListing;
    private javax.swing.JComboBox movieListing;
    private javax.swing.JLabel cineplex;
    private javax.swing.JLabel movie;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea showTime;
	
    public Search() {
        initComponents();
    }
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cineplex = new javax.swing.JLabel();
        movie = new javax.swing.JLabel();
        cineplexListing = new javax.swing.JComboBox();
        movieListing = new javax.swing.JComboBox();
        list = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        showTime = new javax.swing.JTextArea();

        cineplex.setText("Cineplex:");

        movie.setText("Movie:");
        
        List<Cineplex> cineplexList = Cineplex.getAll();
        List<String> cineplexNameList = new ArrayList<String>();
        String cineplexItem = "";
        for (Cineplex cineplex : cineplexList){
        	cineplexItem = cineplex.getId() + " " + cineplex.getName();
        	cineplexNameList.add(cineplexItem); 
        	}
        String[] cineplexNameArray = cineplexNameList.toArray(new String[0]);
        cineplexListing.setModel(new javax.swing.DefaultComboBoxModel(cineplexNameArray));
        cineplexListing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cineplexListingActionPerformed(evt);
            }
        });
        
        String movieItem = "";
        Cineplex defaultCineplex = cineplexList.get(0);
        List<Movie> movieList = getMovieByCineplex(defaultCineplex);
        List<String> movieNameList = new ArrayList<String>();
        for (Movie movie: movieList){
        	movieItem = movie.getId() + " " + movie.getName();
        	movieNameList.add(movieItem); 
        	}
        String[] movieNameArray = movieNameList.toArray(new String[0]);
        movieListing.setModel(new javax.swing.DefaultComboBoxModel(movieNameArray));
//        movieListing.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                movieListingActionPerformed(evt);
//            }
//        }
//        );

        list.setText("List");
        list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listActionPerformed(evt);
            }
        });

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        
        showTime.setEditable(false);
        showTime.setOpaque(false);
        showTime.setColumns(30);
        showTime.setRows(10);
        showTime.setText("Movies listed...");
        jScrollPane1.setViewportView(showTime);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Searching & Listing");

        configuration();
    }                        

    private void cineplexListingActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String selectedCineplex = (String)cineplexListing.getSelectedItem();
        String[] temp = selectedCineplex.split(" ");
        int cineplexId = Integer.parseInt(temp[0]);
        List<Movie> movieList = getMovieByCineplex(cineplexId);
        List<String> movieNameList = new ArrayList<String>();
        String movieItem = "";
        for (Movie movie: movieList){
        	movieItem = movie.getId() + " " + movie.getName();
        	movieNameList.add(movieItem);
        	} 
        String[] movieNameArray = movieNameList.toArray(new String[0]);
        movieListing.setModel(new javax.swing.DefaultComboBoxModel(movieNameArray));
    }                                          

//    private void MovieListingActionPerformed(java.awt.event.ActionEvent evt) {
//    }                                          

    private void listActionPerformed(java.awt.event.ActionEvent evt) {
    	String selectedCineplex = (String)cineplexListing.getSelectedItem();
    	String selectedMovie = (String)movieListing.getSelectedItem();
        String[] temp = selectedCineplex.split(" ");
        int cineplexId = Integer.parseInt(temp[0]);
        temp = selectedMovie.split(" ");
        int movieId = Integer.parseInt(temp[0]);
    	List<Show> showList = Show.getAllByCineplextAndMovie(cineplexId, movieId);
    	
    	//String display = "ShowId  Cinema    Showtime            Type    Rating\n";
    	Movie movie = showList.get(0).getMovie();
    	String display = String.format("Title: %s\nType: %s\nRating: %s\nShowId  Cinema    Showtime\n\n",movie.getName(),movie.getType(),movie.getRating());
    	for(Show show : showList){
    		int Id = show.getId();
    		String cinema = show.getCinema().getCinemaCode();
    		String showtime = show.getShowTime().toString();
    		//String type = show.getMovie().getType();
    		//String rating = show.getMovie().getRating();
    		//String item = String.format("%-8d%-10s%-20s%-8s%s\n",Id,cinema,showtime,type,rating);
    		String item = String.format("%-8d%-10s%-20s\n",Id,cinema,showtime);
    		// System.out.println(item);
    		display += item;
    	}
    	
        showTime.setText(display);
    }                                        

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }
    
    private void configuration(){
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movie Searching", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 204)));
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cineplex)
                    .addComponent(movie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cineplexListing, 0, 220, Short.MAX_VALUE)
                    .addComponent(movieListing, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(list))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cineplexListing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cineplex))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieListing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movie, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(list)))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(close)
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
                .addComponent(close)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    

	public List<Movie> getMovieByCineplex(Cineplex cineplex) {
		return Movie.getAllByCineplex(cineplex, true);
	}

	public List<Movie> getMovieByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getMovieByCineplex(cineplex);
	}
}
