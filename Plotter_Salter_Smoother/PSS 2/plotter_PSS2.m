% Define constants
% a = 6;     
% b = 2;      

minrangenum = 1;
maxrangenum = 12;


% coder changes the case number for every case
filecreate = fopen("plotted_PSS2.csv","w");

xvalues = (minrangenum:maxrangenum)';
y = 6 * 2.^xvalues;

setA = horzcat(xvalues,y);
%creates the first line of file
fprintf(filecreate,'%s,%s\n','X','Y');

fprintf(filecreate, '%f,%f\n', setA);
plot(xvalues,y);
set(gca, lineWidth=1,fontsize=14);
xlabel("X values");
ylabel("y values");
% coder changes the case number for every case
title("Plotting Graph 2");
grid on

fclose(filecreate);