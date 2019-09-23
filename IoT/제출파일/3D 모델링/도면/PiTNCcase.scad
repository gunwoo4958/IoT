// Pi TNC project case

$fs = 0.01;

module standoffs(height=10)
{
	difference()
	{
		cylinder(h=height, r=3.5);
		cylinder(h=height, r=1.3);
	}
}

module pi_standoffs()
{
	translate([ 3.5, 3.5,  0]) standoffs();
	translate([61.5, 3.5,  0]) standoffs();
	translate([ 3.5,52.5,  0]) standoffs();
	translate([61.5,52.5,  0]) standoffs();
}

module lid_vent(len=35)
{
	translate([-0.75,  0,-2]) cube([1.5,len,10]);
	translate([    0,  0, 0]) cylinder(r=0.75, h=10);
	translate([    0,len, 0]) cylinder(r=0.75, h=10);
}

module box()
{
	difference()
	{
		union()
		{
			cube([95,65,2]); // Bottom plate
			translate([0,0,2]) cube([3,65,44]);
			translate([0,62,2]) cube([95,3,44]);
			translate([0,0,2]) cube([95,3,44]);
			translate([92,0,2]) cube([3,65,11]);
		}
		translate([7+4.6,-1,11]) cube([12,5,8]);
		translate([7+23,-1,12]) cube([18,5,10]);
		translate([7+53.5-5,-1,12]) cube([10,5,10]);
		translate([-1,3.5+19,8]) cube([5,16,6]);
		translate([42.5,-1,39])
			cube([6,70,3]);
		translate([70,32.5,-1])
			cylinder(h=5, r=2);
		translate([70-(26.4*2),32.5,-1])
			cylinder(h=5, r=2);
	}
	translate([7,3.5,2]) pi_standoffs();
}

module lid()
{
	difference()
	{
		union()
		{
			cube([95,65,2]);
			translate([3,3,2])
				cube([92,59,1]);
			translate([42.5,3.5,3])
				cube([6,3,6]);
			translate([42.5,3.5,7.5])
				rotate(a=[0,90,0])
					cylinder(h=6,r=1.5);
			translate([42.5,65-6.5,3])
				cube([6,3,6]);
			translate([42.5,65-3.5,7.5])
				rotate(a=[0,90,0])
					cylinder(h=6,r=1.5);
			translate([92,3,3])
				cube([3,8,12]);
			translate([92,57,3])
				cube([3,5,12]);
		}
		for ( i = [0 : 9] )
		{
			translate([10+(8*i),10,-1]) lid_vent(len=45);
		}
	}
}

//box();
translate([0,-66,0])
	lid();