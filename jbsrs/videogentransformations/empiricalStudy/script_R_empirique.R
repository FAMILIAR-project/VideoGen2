# Authors : jbsrs

###### Corrélation Somme des tailles des videos VS Taille réelle de la variante #######
# Adapter le path pour chercher le fichier csv à analyser
setwd("/home/june/Documents/MasterIL/VideoGen2/jbsrs/videogentransformations/empiricalStudy/analyses/")
file = read.csv("analysis_realsize.csv", header = TRUE, sep= ";")
data = file[, c(8,9)]
cor_val = cor(size, realSize)
cor_mat = cor(data, method= "pearson")
cor_p = cor.test(size, realSize)
cor_p
plot(size, realSize, xlab="sum of the size of the videos in a playlist in octet", ylab="realSize of the playlist in octet")
abline(lm(realSize ~ size))

###### Corrélation Variantes sans filtre VS variantes filtre negate #######
file2 = read.csv("analysis_filter_negate.csv", header= TRUE, sep=";")
data_negate = file2[, c(8,9)]
cor_val_negate = cor(data$realSize, data_negate$realSize)
cor_p_negate = cor.test(data$realSize, data_negate$realSize)
cor_p_negate

###### Corrélation Variantes sans filtre VS variantes filtre bw #######
file3 = read.csv("analysis_filter_bw.csv", header= TRUE, sep=";")
data_bw = file3[, c(8,9)]
cor_val_bw = cor(data$realSize, data_bw$realSize)
cor_p_bw = cor.test(data$realSize, data_bw$realSize)
cor_p_bw

###### Corrélation Variantes sans filtre VS variantes filtre flip h #######
file4 = read.csv("analysis_filter_hflip.csv", header= TRUE, sep=";")
data_hflip = file4[, c(8,9)]
cor_val_hflip = cor(data$realSize, data_hflip$realSize)
cor_p_hflip = cor.test(data$realSize, data_hflip$realSize)
cor_p_hflip
###### Corrélation Variantes sans filtre VS variantes filtre flip v #######
file5 = read.csv("analysis_filter_vflip.csv", header= TRUE, sep=";")
data_vflip = file5[, c(8,9)]
cor_val_vflip = cor(data$realSize, data_vflip$realSize)
cor_p_vflip = cor.test(data$realSize, data_vflip$realSize)
cor_p_vflip

###### Corrélation Variantes video VS variantes gif #######
file6 = read.csv("analysis_gif.csv", header= TRUE, sep=";")
data_gif = file6[, c(8,9)]
cor_val_gif = cor(data$realSize, data_gif$realSize)
cor_p_gif = cor.test(data$realSize, data_gif$realSize)
cor_p_gif
